package com.finder.innox.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.CategoryDTO;
import com.finder.innox.core.dto.SubCategoryDTO;
import com.finder.innox.core.services.CategoryService;
import com.finder.innox.core.services.SubCategoryService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.CategoryListResponse;
import com.finder.innox.response.CategoryResponse;
import com.finder.innox.response.Response;
import com.finder.innox.response.SubCategoryListResponse;
import com.finder.innox.response.SubCategoryResponse;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class CategoryApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping(path = InnoxApiConstant.API_CATEGORY_LIST)
	public String getCategoryList(HttpServletRequest request) {
		logger.info("getCategoryList() Start");
		String result = "";
		ProcessException pe = null;
		Response<CategoryListResponse> apiResponse = new Response<CategoryListResponse>();
		List<CategoryDTO> categoryList = categoryService.getAllCategoryList();

		CategoryListResponse resposne = new CategoryListResponse();
		
		categoryList.forEach(cat -> {
			CategoryResponse categoryResponse = new CategoryResponse(cat, request);
			resposne.getCategorys().add(categoryResponse);

			List<SubCategoryDTO> subCategoryDtoList = subCategoryService.getAllSubCategoryListByCatId(cat.getSeq(),
					CommonStatus.ACTIVE.getCode());
			subCategoryDtoList.forEach(sub -> {
				SubCategoryResponse subCategoryResponse = new SubCategoryResponse(sub, request);
				categoryResponse.getSub_categorys().add(subCategoryResponse);
			});
		});

		apiResponse.setData(resposne);
		apiResponse.setResponseMessage("Data retrival is success");

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		logger.info("getCategoryList() End");
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_SUB_CATEGORY_LIST)
	public String getSubCategoryListByCategoryId(@RequestParam(name = "category_id") Long category_id,
			HttpServletRequest request, HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<SubCategoryListResponse> apiResponse = new Response<SubCategoryListResponse>();

		if (category_id == null || category_id <= 0) {
			errorList.add(
					new FieldError(FieldCode.CATEGORY_ID.getCode(), ErrorMessage.CATEGORY_ID_REQUIRED.getMessage()));
		}

		if (errorList.size() == 0) {

			List<SubCategoryDTO> subCategoryDtoList = subCategoryService.getAllSubCategoryListByCatId(category_id,
					CommonStatus.ACTIVE.getCode());
			SubCategoryListResponse response = new SubCategoryListResponse();

			if (subCategoryDtoList != null && !subCategoryDtoList.isEmpty()) {

				response.setCategory_id(category_id);

				subCategoryDtoList.forEach(sub -> {
					SubCategoryResponse subCategoryResponse = new SubCategoryResponse(sub, request);
					response.getSub_categroys().add(subCategoryResponse);
				});

				apiResponse.setData(response);
				apiResponse.setResponseMessage("Data retrival is success");
			} else {
				httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
			}

		} else {
			httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
			pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
			pe.setFieldErrorList(errorList);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

package com.finder.ecoshop.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.finder.ecoshop.annotation.InnoxShopApi;
import com.finder.ecoshop.core.dto.CategoryDTO;
import com.finder.ecoshop.core.dto.SubCategoryDTO;
import com.finder.ecoshop.core.services.CategoryService;
import com.finder.ecoshop.core.services.SubCategoryService;
import com.finder.ecoshop.exception.ProcessException;
import com.finder.ecoshop.exception.ProcessException.ErrorType;
import com.finder.ecoshop.request.SubCategoryRequest;
import com.finder.ecoshop.response.CategoryListResponse;
import com.finder.ecoshop.response.CategoryResponse;
import com.finder.ecoshop.response.Response;
import com.finder.ecoshop.response.SubCategoryListResponse;
import com.finder.ecoshop.response.SubCategoryResponse;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.FieldError;
import com.finder.ecoshop.utils.FieldError.ErrorMessage;
import com.finder.ecoshop.utils.FieldError.FieldCode;
import com.finder.ecoshop.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class CategoryApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@PostMapping(path = InnoxApiConstant.API_CATEGORY_LIST)
	public String getCategoryList(HttpServletRequest request) {
		logger.info("getCategoryList() Start");
		String result = "";
		ProcessException pe = null;
		Response<CategoryListResponse> apiResponse = new Response<CategoryListResponse>();
		List<CategoryDTO> categoryList = categoryService.getAllCategoryList();

		CategoryListResponse resposne = new CategoryListResponse();
		categoryList.forEach(cat -> {
			CategoryResponse categoryResponse = new CategoryResponse(cat, request);
			resposne.getCategory_list().add(categoryResponse);
		});

		apiResponse.setData(resposne);
		apiResponse.setResponseMessage("Data retrival is success");

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		logger.info("getCategoryList() End");
		return result;
	}

	@PostMapping(path = InnoxApiConstant.API_SUB_CATEGORY_LIST)
	public String getSubCategoryListByCategoryId(@RequestBody SubCategoryRequest subCategoryRequest,
			HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<SubCategoryListResponse> apiResponse = new Response<SubCategoryListResponse>();

		if (subCategoryRequest.getCategory_id() == null || subCategoryRequest.getCategory_id() <= 0) {
			errorList.add(
					new FieldError(FieldCode.CATEGORY_ID.getCode(), ErrorMessage.CATEGORY_ID_REQUIRED.getMessage()));
		}

		if (errorList.size() == 0) {

			List<SubCategoryDTO> subCategoryDtoList = subCategoryService.getAllSubCategoryListByCatId(subCategoryRequest.getCategory_id(),
					CommonStatus.ACTIVE.getCode());
			SubCategoryListResponse response = new SubCategoryListResponse();
			if (subCategoryDtoList != null && !subCategoryDtoList.isEmpty()) {

				response.setCategory_id(subCategoryRequest.getCategory_id());

				subCategoryDtoList.forEach(sub -> {
					SubCategoryResponse subCategoryResponse = new SubCategoryResponse(sub, request);
					response.getSub_categroy_list().add(subCategoryResponse);
				});
				
				apiResponse.setData(response);
				apiResponse.setResponseMessage("Data retrival is success");
			} else {
				pe = new ProcessException(ErrorType.INVALID_DATA);
			}

		} else {
			pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
			pe.setFieldErrorList(errorList);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

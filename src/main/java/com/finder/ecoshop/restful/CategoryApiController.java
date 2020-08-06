package com.finder.ecoshop.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.ecoshop.annotation.InnoxShopApi;
import com.finder.ecoshop.core.dto.CategoryDTO;
import com.finder.ecoshop.core.services.CategoryService;
import com.finder.ecoshop.exception.ProcessException;
import com.finder.ecoshop.response.CategoryListResponse;
import com.finder.ecoshop.response.CategoryResponse;
import com.finder.ecoshop.response.Response;
import com.finder.ecoshop.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class CategoryApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CategoryService categoryService;

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
			resposne.getCategory_list().add(categoryResponse);
		});

		apiResponse.setData(resposne);
		apiResponse.setResponseMessage("Data retrival is success");

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		logger.info("getCategoryList() End");
		return result;
	}

}

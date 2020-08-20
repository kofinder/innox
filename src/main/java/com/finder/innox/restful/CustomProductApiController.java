package com.finder.innox.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.CustomProductDTO;
import com.finder.innox.core.services.CustomProductService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.ProductListResponse;
import com.finder.innox.response.ProductResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class CustomProductApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CustomProductService cusProductService;

	@GetMapping(path = InnoxApiConstant.API_CUSTOM_PRODUCT_LIST)
	public String getCustomProductListBySubCategory(@RequestParam(name = "sub_category_id") Long sub_category_id,
			HttpServletRequest request) {
		logger.info("getCustomProductListBySubCategory () >> Start");
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<ProductListResponse> apiResponse = new Response<ProductListResponse>();

		if (sub_category_id == null || sub_category_id <= 0) {
			errorList.add(new FieldError(FieldCode.SUB_CATEGORY_ID.getCode(),
					ErrorMessage.SUB_CATEGORY_ID_REQUIRED.getMessage()));
		}

		if (errorList.size() == 0) {
			ProductListResponse response = new ProductListResponse();
			List<CustomProductDTO> dtoList = cusProductService.getCustomProductListBySubCat(sub_category_id);

			dtoList.forEach(cusPrd -> {
				ProductResponse customProductResponse = new ProductResponse(cusPrd, request);
				response.getProducts().add(customProductResponse);
			});

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Data retrieval is success");
		} else {
			pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
			pe.setFieldErrorList(errorList);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		logger.info("getCustomProductListBySubCategory () >> End");
		return result;
	}

}

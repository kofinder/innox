package com.finder.ecoshop.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.ecoshop.annotation.InnoxShopApi;
import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.core.services.ProductService;
import com.finder.ecoshop.exception.ProcessException;
import com.finder.ecoshop.exception.ProcessException.ErrorType;
import com.finder.ecoshop.response.ProductListResponse;
import com.finder.ecoshop.response.ProductResponse;
import com.finder.ecoshop.response.Response;
import com.finder.ecoshop.utils.FieldError;
import com.finder.ecoshop.utils.FieldError.ErrorMessage;
import com.finder.ecoshop.utils.FieldError.FieldCode;
import com.finder.ecoshop.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class ProductApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_LIST)
	public String getProductList(@RequestParam(name = "pageNo") int pageNo, HttpServletRequest request) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<ProductListResponse> apiResponse = new Response<>();

		try {
			ProductListResponse response = new ProductListResponse();
			if (pageNo <= 0) {
				errorList.add(new FieldError(FieldCode.PAGE_NO.getCode(), ErrorMessage.PAGE_NO_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				List<ProductDTO> productDtoList = productService.getProductListByPageNo(pageNo);
				productDtoList.forEach(prodcut -> {
					ProductResponse productResponse = new ProductResponse(prodcut, request);
					response.getProduct_list().add(productResponse);
				});
			} else {
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
				pe.setFieldErrorList(errorList);
			}

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Data retrieval is success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getProductList() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}
}

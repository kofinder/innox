package com.finder.innox.restful;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.ProductDTO;
import com.finder.innox.core.services.ProductService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.ProductDetailResponse;
import com.finder.innox.response.ProductListResponse;
import com.finder.innox.response.ProductResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;

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
					response.getProducts().add(productResponse);
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

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_LIST_BY_SUB_CATEGORY)
	public String getProductListBySubCategory(@RequestParam(name = "sub_category_id") Long sub_category_id,
			HttpServletRequest request) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<ProductListResponse> apiResponse = new Response<>();

		try {
			ProductListResponse response = new ProductListResponse();
			if (sub_category_id == null || sub_category_id <= 0) {
				errorList.add(new FieldError(FieldCode.SUB_CATEGORY_ID.getCode(),
						ErrorMessage.SUB_CATEGORY_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				List<ProductDTO> productDtoList = productService.getProductListBySubCatgory(sub_category_id);

				productDtoList.forEach(prodcut -> {
					ProductResponse productResponse = new ProductResponse(prodcut, request);
					response.getProducts().add(productResponse);
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

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_LIST_SEARCH)
	public String productListSearch(@RequestParam(name = "keyword", required = false) String searchKeyword,
			@RequestParam(name = "startPrice", required = false) BigDecimal startPrice,
			@RequestParam(name = "endPrice", required = false) BigDecimal endPrice,
			@RequestParam(name = "category_id", required = false) Long category_id,
			@RequestParam(name = "sub_category_id", required = false) Long sub_category_id,
			HttpServletRequest request) {

		String result = "";
		ProcessException pe = null;
		Response<ProductListResponse> apiReeponse = new Response<ProductListResponse>();
		try {
			ProductListResponse response = new ProductListResponse();
			List<ProductDTO> dtoList = productService.searchProductList(searchKeyword, startPrice, endPrice,
					category_id, sub_category_id);

			dtoList.forEach(dto -> {
				ProductResponse productResponse = new ProductResponse(dto, request);
				response.getProducts().add(productResponse);
			});

			apiReeponse.setData(response);
			apiReeponse.setResponseMessage("Data retrieval is success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("productListSearch() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiReeponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_DETAIL)
	public String productDetail(@RequestParam(name = "product_id") Long product_id, HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<ProductDetailResponse> apiResponse = new Response<ProductDetailResponse>();

		try {

			if (product_id == null || product_id <= 0) {
				errorList.add(
						new FieldError(FieldCode.PRODUCT_ID.getCode(), ErrorMessage.PRODUCT_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {

				ProductDTO productDtoDetail = productService.getProductDataById(product_id);

				if (productDtoDetail != null) {
					ProductDetailResponse response = new ProductDetailResponse(productDtoDetail, request);

					apiResponse.setData(response);
					apiResponse.setResponseMessage("Data retrieval is success");
				} else {
					pe = new ProcessException(ErrorType.INVALID_DATA);
				}
			} else {
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("productDetail() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}
}

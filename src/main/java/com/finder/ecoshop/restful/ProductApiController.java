package com.finder.ecoshop.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.ecoshop.annotation.InnoxShopApi;
import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.core.services.ProductService;
import com.finder.ecoshop.exception.ProcessException;
import com.finder.ecoshop.exception.ProcessException.ErrorType;
import com.finder.ecoshop.request.ProductRequest;
import com.finder.ecoshop.response.ProductDetailResponse;
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

	@PostMapping(path = InnoxApiConstant.API_PRODUCT_LIST)
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

	@PostMapping(path = InnoxApiConstant.API_PRODUCT_LIST_BY_SUB_CATEGORY)
	public String getProductListBySubCategory(@RequestBody ProductRequest productRequest, HttpServletRequest request) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<ProductListResponse> apiResponse = new Response<>();

		try {
			ProductListResponse response = new ProductListResponse();
			if (productRequest.getSub_category_id() == null || productRequest.getSub_category_id() <= 0) {
				errorList.add(new FieldError(FieldCode.SUB_CATEGORY_ID.getCode(),
						ErrorMessage.SUB_CATEGORY_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				List<ProductDTO> productDtoList = productService
						.getProductListBySubCatgory(productRequest.getSub_category_id());

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

	@PostMapping(path = InnoxApiConstant.API_PRODUCT_LIST_SEARCH)
	public String productListSearch(@RequestBody ProductRequest searchProduct, HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		Response<ProductListResponse> apiReeponse = new Response<ProductListResponse>();
		try {
			ProductListResponse response = new ProductListResponse();
			List<ProductDTO> dtoList = productService.searchProductList(searchProduct.getSearchKeyword(),
					searchProduct.getStartPrice(), searchProduct.getEndPrice(), searchProduct.getCategory_id(),
					searchProduct.getSub_category_id());

			dtoList.forEach(dto -> {
				ProductResponse productResponse = new ProductResponse(dto, request);
				response.getProduct_list().add(productResponse);
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

	@PostMapping(path = InnoxApiConstant.API_PRODUCT_DETAIL)
	public String productDetail(@RequestBody ProductRequest productRequest, HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<ProductDetailResponse> apiResponse = new Response<ProductDetailResponse>();

		try {

			if (productRequest.getProduct_id() == null || productRequest.getProduct_id() <= 0) {
				errorList.add(
						new FieldError(FieldCode.PRODUCT_ID.getCode(), ErrorMessage.PRODUCT_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {

				ProductDTO productDtoDetail = productService.getProductDataById(productRequest.getProduct_id());

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

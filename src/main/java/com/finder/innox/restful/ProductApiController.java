package com.finder.innox.restful;

import java.math.BigDecimal;
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
import com.finder.innox.core.dto.ProductDTO;
import com.finder.innox.core.services.ProductService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.ProductDetailResponse;
import com.finder.innox.response.ProductListResponse;
import com.finder.innox.response.ProductResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class ProductApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_LIST, produces = "application/json; charset=utf-8")
	public String getProductList(@RequestParam(name = "pageNo") int pageNo, HttpServletRequest request,
			HttpServletResponse httpResponse) {
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
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Data retrieval is success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getProductList() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_LIST_BY_SUB_CATEGORY,produces = "application/json; charset=utf-8")
	public String getProductListBySubCategory(@RequestParam(name = "sub_category_id") Long sub_category_id,
			@RequestParam(name = "page_no", required = false) Integer page_no, HttpServletRequest request,
			HttpServletResponse httpResponse) {
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
				List<ProductDTO> productDtoList = productService.getProductListBySubCatgory(sub_category_id, page_no);

				productDtoList.forEach(prodcut -> {
					ProductResponse productResponse = new ProductResponse(prodcut, request);
					response.getProducts().add(productResponse);
				});

				if (page_no > 0) {
					// get product list count
					List<ProductDTO> prdDtoList = productService.getProductListByPageNo(0);
					int listCount = prdDtoList.size();
					int currentCount = page_no * CommonConstant.ROW_PER_PAGE;
					response.setTotal_count(listCount);

					if (currentCount < listCount) {
						response.setHas_more_list(true);
					}
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Data retrieval is success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getProductList() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_LIST_SEARCH, produces = "application/json; charset=utf-8")
	public String productListSearch(@RequestParam(name = "keyword", required = false) String searchKeyword,
			@RequestParam(name = "startPrice", required = false) BigDecimal startPrice,
			@RequestParam(name = "endPrice", required = false) BigDecimal endPrice,
			@RequestParam(name = "category_id", required = false) Long category_id,
			@RequestParam(name = "sub_category_id", required = false) Long sub_category_id, HttpServletRequest request,
			HttpServletResponse httpResponse) {

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
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiReeponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_PRODUCT_DETAIL, produces = "application/json; charset=utf-8")
	public String productDetail(@RequestParam(name = "product_id") Long product_id, HttpServletRequest request,
			HttpServletResponse httpResponse) {
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
					httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("productDetail() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}
}

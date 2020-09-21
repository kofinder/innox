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
import com.finder.innox.core.dto.CustomItemDTO;
import com.finder.innox.core.dto.CustomItemLayoutDTO;
import com.finder.innox.core.dto.CustomProductDTO;
import com.finder.innox.core.services.CustomItemLayoutService;
import com.finder.innox.core.services.CustomItemService;
import com.finder.innox.core.services.CustomProductService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.CustomItemLayoutResponse;
import com.finder.innox.response.CustomItemResponse;
import com.finder.innox.response.CustomProductDetailResponse;
import com.finder.innox.response.ProductListResponse;
import com.finder.innox.response.ProductResponse;
import com.finder.innox.response.Response;
import com.finder.innox.response.SizeResponse;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class CustomProductApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CustomProductService cusProductService;

	@Autowired
	private CustomItemService cusItemService;

	@Autowired
	private CustomItemLayoutService cusItemLayoutService;

	@GetMapping(path = InnoxApiConstant.API_CUSTOM_PRODUCT_LIST)
	public String getCustomProductListBySubCategory(@RequestParam(name = "sub_category_id") Long sub_category_id,
			HttpServletRequest request, HttpServletResponse httpResponse) {
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
			httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
			pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
			pe.setFieldErrorList(errorList);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		logger.info("getCustomProductListBySubCategory () >> End");
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_CUSTOM_PRODUCT_DETAIl)
	public String getCustomProductDetail(@RequestParam(name = "custom_product_id") Long custom_product_id,
			HttpServletRequest request, HttpServletResponse httpResponse) {
		logger.info("getCustomProductDetail() >> Start >> Custom Product Id : " + custom_product_id);
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<CustomProductDetailResponse> apiResponse = new Response<CustomProductDetailResponse>();

		if (custom_product_id == null || custom_product_id.compareTo(Long.valueOf(0)) != 1) {
			errorList.add(new FieldError(FieldCode.CUSTOM_PRODUCT_ID.getCode(),
					ErrorMessage.CUSTOM_PRODUCT_ID_REQUIRED.getMessage()));
		}

		try {
			if (errorList.size() == 0) {
				CustomProductDetailResponse customProductDetailResponse = null;
				// custom product
				CustomProductDTO customProductDTO = cusProductService.getCustomProductById(custom_product_id, 0);
				if (customProductDTO != null) {
					customProductDetailResponse = new CustomProductDetailResponse(customProductDTO);

					// custom item list
					List<CustomItemDTO> customItemDTOs = cusItemService
							.getCustomItemListByCustomProductId(custom_product_id, 0);

					for (CustomItemDTO itemDTO : customItemDTOs) {
						CustomItemResponse customItemResponse = new CustomItemResponse(itemDTO);

						// custom item size TODO need to get data list
						itemDTO.getCustomItemSizeList().forEach(sizeDTO -> {
							SizeResponse sizeResponse = new SizeResponse(sizeDTO.getSizeDTO());
							customItemResponse.getCustom_sizes().add(sizeResponse);
						});

						// custom item layout
						List<CustomItemLayoutDTO> itemLayoutDTOs = cusItemLayoutService
								.getCustomItemLayoutListByItemId(itemDTO.getSeq(), CommonStatus.ACTIVE.getCode());

						for (CustomItemLayoutDTO layoutDTO : itemLayoutDTOs) {
							CustomItemLayoutResponse customItemLayoutResponse = new CustomItemLayoutResponse(layoutDTO,
									request);
							customItemResponse.getCustom_item_layouts().add(customItemLayoutResponse);
						}

						customProductDetailResponse.getCustom_items().add(customItemResponse);
					}

					apiResponse.setData(customProductDetailResponse);
					apiResponse.setResponseMessage("Data retrieval is success");
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCustomProductDetail() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		logger.info("getCustomProductDetail() >> End");
		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

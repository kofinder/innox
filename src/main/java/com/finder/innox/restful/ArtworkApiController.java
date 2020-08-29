package com.finder.innox.restful;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.ArtworkCategoryDTO;
import com.finder.innox.core.dto.ArtworkDTO;
import com.finder.innox.core.services.ArtworkCategoryService;
import com.finder.innox.core.services.ArtworkService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.request.ArtworkUploadRequest;
import com.finder.innox.response.ArtworkCategoryListResponse;
import com.finder.innox.response.ArtworkCategoryResponse;
import com.finder.innox.response.ArtworkListResponse;
import com.finder.innox.response.ArtworkResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class ArtworkApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ArtworkService artworkService;

	@Autowired
	private ArtworkCategoryService artworkCategroyService;

	@PostMapping(path = InnoxApiConstant.API_ARTWORK_UPLOAD)
	public String uploadArtwork(@RequestBody ArtworkUploadRequest artworkUplaod, HttpServletRequest request) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<String> apiResponse = new Response<String>();

		try {

			if (artworkUplaod.getDesigner_id() == null || artworkUplaod.getDesigner_id() < 0) {
				errorList.add(
						new FieldError(FieldCode.DESIGNER.getCode(), ErrorMessage.DESIGNER_ID_REQUIRED.getMessage()));
			}

			if (CommonUtil.isEmpty(artworkUplaod.getArtwork_name())) {
				errorList.add(
						new FieldError(FieldCode.ARTWORK.getCode(), ErrorMessage.ARTWORK_NAME_REQUIRED.getMessage()));
			}

			if (artworkUplaod.getOriginal_price() == null
					|| artworkUplaod.getOriginal_price().compareTo(BigDecimal.ZERO) != 1) {
				errorList.add(
						new FieldError(FieldCode.ARTWORK.getCode(), ErrorMessage.ARTWORK_PRICE_REQUIRED.getMessage()));
			}

			if (CommonUtil.isEmpty(artworkUplaod.getArtwork_image())) {
				errorList.add(
						new FieldError(FieldCode.ARTWORK.getCode(), ErrorMessage.ARTWORK_IMAGE_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				ArtworkDTO artworkDTO = artworkService
						.uploadArtwork(ArtworkUploadRequest.convertRequestToDTO(artworkUplaod));
				if (artworkDTO != null) {
					apiResponse.setData("Artwork upload is successful");
				} else {
					apiResponse.setData("Artwork upload is failed");
				}
			} else {
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadArtwork() >> " + e.getMessage(), e);

		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_ARTWORK_CATEGORY)
	public String getArtworkCategoryList(HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		Response<ArtworkCategoryListResponse> apiResponse = new Response<ArtworkCategoryListResponse>();
		ArtworkCategoryListResponse response = new ArtworkCategoryListResponse();

		List<ArtworkCategoryDTO> categoryDtoList = artworkCategroyService.getArtworkCategoryList();
		categoryDtoList.forEach(dto -> {
			ArtworkCategoryResponse artworkCategory = new ArtworkCategoryResponse();
			artworkCategory.setCategroy_id(dto.getSeq());
			artworkCategory.setCategory_name(dto.getCategoryName());
			artworkCategory.setCategory_code(dto.getCateogryCode());

			response.getArtwork_categorys().add(artworkCategory);
		});

		apiResponse.setData(response);
		apiResponse.setResponseMessage("Data retrieval is success");

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_ARTWORK_LIST_BY_CATEGORY)
	public String getArtworkListByCategory(@RequestParam(name = "artwork_category_id") Long artwork_category_id,
			HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<ArtworkListResponse> apiResponse = new Response<ArtworkListResponse>();
		ArtworkListResponse artworkListResponse = new ArtworkListResponse();

		try {
			if (artwork_category_id == null || artwork_category_id.compareTo(Long.valueOf(0)) != 1) {
				errorList.add(new FieldError(FieldCode.ARTWORK_CATEGORY.getCode(),
						ErrorMessage.ARTWORK_CATEGORY_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				List<ArtworkDTO> dtoList = artworkService.getArtworkListByCategory(artwork_category_id);

				dtoList.forEach(dto -> {
					ArtworkResponse artworkResponse = new ArtworkResponse(dto, request);
					artworkListResponse.getArtworks().add(artworkResponse);
				});

				apiResponse.setData(artworkListResponse);
				apiResponse.setResponseMessage("Data retrieval is success");
			} else {
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
				pe.setFieldErrorList(errorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getArtworkListByCategory() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_ARTWORK_LIST_BY_DESIGNER)
	public String getArtworkListByDesigner(@RequestParam(name = "designer_id") Long designer_id,
			HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<ArtworkListResponse> apiResponse = new Response<ArtworkListResponse>();
		ArtworkListResponse artworkListResponse = new ArtworkListResponse();

		if (designer_id == null || designer_id.compareTo(Long.valueOf(0)) != 1) {
			errorList.add(new FieldError(FieldCode.DESIGNER.getCode(), ErrorMessage.DESIGNER_ID_REQUIRED.getMessage()));
		}

		if (errorList.size() == 0) {
			List<ArtworkDTO> dtoList = artworkService.getArtworkListByDesigner(designer_id);

			dtoList.forEach(artwork -> {
				ArtworkResponse artworkResponse = new ArtworkResponse(artwork, request);
				artworkListResponse.getArtworks().add(artworkResponse);
			});

			apiResponse.setData(artworkListResponse);
			apiResponse.setResponseMessage("Data retrieval is success");
		} else {
			pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
			pe.setFieldErrorList(errorList);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

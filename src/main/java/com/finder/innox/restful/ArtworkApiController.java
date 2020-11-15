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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;

@RestController
public class ArtworkApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ArtworkService artworkService;

	@Autowired
	private ArtworkCategoryService artworkCategroyService;

	@PostMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME + InnoxApiConstant.API_ARTWORK_UPLOAD)
	public String uploadArtwork(@RequestBody ArtworkUploadRequest artworkUplaod, HttpServletRequest request,
			HttpServletResponse httpResponse) {
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
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("uploadArtwork() >> " + e.getMessage(), e);

		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_RESOURCES_NAME + InnoxApiConstant.API_ARTWORK_CATEGORY)
	public String getArtworkCategoryList(HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		Response<ArtworkCategoryListResponse> apiResponse = new Response<ArtworkCategoryListResponse>();
		ArtworkCategoryListResponse response = new ArtworkCategoryListResponse();

		List<ArtworkCategoryDTO> categoryDtoList = artworkCategroyService.getArtworkCategoryList();
		categoryDtoList.forEach(dto -> {
			ArtworkCategoryResponse artworkCategory = new ArtworkCategoryResponse();
			artworkCategory.setCategory_id(dto.getSeq());
			artworkCategory.setCategory_name(dto.getCategoryName());
			artworkCategory.setCategory_code(dto.getCateogryCode());

			response.getArtwork_categorys().add(artworkCategory);
		});

		apiResponse.setData(response);
		apiResponse.setResponseMessage("Data retrieval is success");

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_RESOURCES_NAME + InnoxApiConstant.API_ARTWORK_LIST_BY_CATEGORY)
	public String getArtworkListByCategory(@RequestParam(name = "artwork_category_id") Long artwork_category_id,
			HttpServletRequest request, HttpServletResponse httpResponse) {
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
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getArtworkListByCategory() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_RESOURCES_NAME + InnoxApiConstant.API_ARTWORK_LIST_BY_DESIGNER)
	public String getArtworkListByDesigner(@RequestParam(name = "designer_id") Long designer_id,
			HttpServletRequest request, HttpServletResponse httpResponse) {
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
			httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
			pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
			pe.setFieldErrorList(errorList);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME
			+ InnoxApiConstant.API_SEARCH_ARTWORK_LIST, produces = "application/json; charset=utf-8")
	public String searchArtworkList(@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "status", required = false) Integer status,
			@RequestParam(name = "pageNo", required = false) Integer pageNo, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<ArtworkListResponse> apiResponse = new Response<ArtworkListResponse>();
		ArtworkListResponse artworkListResponse = new ArtworkListResponse();

		try {

			if (CommonUtil.isEmpty(httpRequest.getHeader(CommonConstant.API_REQUEST_HEADER_DESIGNER_ID))) {
				errorList.add(
						new FieldError(FieldCode.DESIGNER.getCode(), ErrorMessage.DESIGNER_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				Long designerId = Long.valueOf(httpRequest.getHeader(CommonConstant.API_REQUEST_HEADER_DESIGNER_ID));

				List<ArtworkDTO> dtoList = artworkService.searchArtworkList(startDate, endDate, status, designerId,
						pageNo);

				dtoList.forEach(artwork -> {
					ArtworkResponse artworkResponse = new ArtworkResponse(artwork, httpRequest);
					artworkListResponse.getArtworks().add(artworkResponse);
				});

				apiResponse.setData(artworkListResponse);
				apiResponse.setResponseMessage("Data retrieval is success");
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("searchArtworkList() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME
			+ InnoxApiConstant.API_ARTWORK_DETAIL, produces = "application/json; charset=utf-8")
	public String artworkDetail(@RequestParam(name = "artwork_id", required = false) Long artworkId, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<ArtworkResponse> apiResponse = new Response<ArtworkResponse>();
		ArtworkResponse artworkResponse = null;

		try {

			if (artworkId == null || artworkId.compareTo(0l) <= 0) {
				errorList.add(
						new FieldError(FieldCode.ARTWORK.getCode(), ErrorMessage.ARTWORK_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				ArtworkDTO artworkDTO = artworkService.getArtworkDataById(artworkId);
				if (artworkDTO != null) {
					artworkResponse = new ArtworkResponse(artworkDTO, httpRequest);
					apiResponse.setData(artworkResponse);
					apiResponse.setResponseMessage("Data retrieval is success");
				} else {
					httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("searchArtworkList() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

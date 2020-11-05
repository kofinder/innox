package com.finder.innox.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.AnnouncementDTO;
import com.finder.innox.core.services.AnnouncementService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.AnnouncementListResponse;
import com.finder.innox.response.AnnouncementResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.JsonUtil;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class AnnouncementApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AnnouncementService announcementService;

	@GetMapping(path = InnoxApiConstant.API_ANNOUNCEMENT_LIST, produces = "application/json; charset=utf-8")
	public String announcementListGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		logger.info("announcementListGet() >> Start");
		String result = "";
		Response<AnnouncementListResponse> apiResponse = new Response<AnnouncementListResponse>();
		ProcessException pe = null;

		AnnouncementDTO announcementDTO = new AnnouncementDTO();
		announcementDTO.setStatus(CommonStatus.ACTIVE.getCode());

		AnnouncementListResponse announcementListResponse = new AnnouncementListResponse();
		List<AnnouncementDTO> announcementDTOs = announcementService.searchAnnouncementData(announcementDTO);
		announcementDTOs.forEach(announcement -> {
			AnnouncementResponse announcementResponse = new AnnouncementResponse();
			announcementResponse.setAnnouncement_id(announcement.getSeq());
			announcementResponse.setNoti_type(announcement.getNotiType());
			announcementResponse.setNoti_type_text(announcement.getNotiTypeText());
			announcementResponse.setTitle(announcement.getTitle());
			announcementResponse.setDescription(announcement.getDescription());
			announcementResponse.setCreated_date(announcement.getCreatedDate());
			announcementResponse
					.setSummary_image(CommonUtil.prepareImagePath(announcement.getSummaryImage(), httpRequest));

			announcementListResponse.getAnnouncements().add(announcementResponse);
		});

		apiResponse.setData(announcementListResponse);

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		logger.info("announcementListGet() >> End");
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_ANNOUNCEMENT_DETAIL)
	public String announcementDetail(@PathVariable(name = "announcement_id") Long announcementId,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String result = "";
		Response<AnnouncementResponse> apiResponse = new Response<AnnouncementResponse>();
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;

		try {

			if (announcementId == null || announcementId.compareTo(0L) < 0) {
				errorList.add(new FieldError(FieldCode.ANNOUNCEMENT_ID.getCode(),
						ErrorMessage.ANNOUNCEMENT_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				AnnouncementDTO announcement = announcementService.getDataById(announcementId);

				if (announcement != null) {
					AnnouncementResponse announcementResponse = new AnnouncementResponse();
					announcementResponse.setAnnouncement_id(announcement.getSeq());
					announcementResponse.setAnnouncement_id(announcement.getSeq());
					announcementResponse.setNoti_type(announcement.getNotiType());
					announcementResponse.setTitle(announcement.getTitle());
					announcementResponse.setDescription(announcement.getDescription());
					announcementResponse.setCreated_date(announcement.getCreatedDate());
					announcementResponse
							.setSummary_image(CommonUtil.prepareImagePath(announcement.getSummaryImage(), httpRequest));
					announcementResponse
							.setDetail_image(CommonUtil.prepareImagePath(announcement.getDetailImage(), httpRequest));

					apiResponse.setData(announcementResponse);
				} else {
					httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("announcementDetail() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

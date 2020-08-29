package com.finder.innox.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.FontsDTO;
import com.finder.innox.core.services.FontsService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.FontListResponse;
import com.finder.innox.response.FontResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class FontApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FontsService fontsService;

	@GetMapping(path = InnoxApiConstant.API_FONT_LIST)
	public String getFontList(HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		Response<FontListResponse> apiResponse = new Response<FontListResponse>();
		FontListResponse fontListResponse = new FontListResponse();

		try {
			List<FontsDTO> fontDtos = fontsService.getFontList(CommonStatus.ACTIVE.getCode());
			fontDtos.forEach(font -> {
				FontResponse fontResponse = new FontResponse();
				fontResponse.setFont_id(font.getSeq());
				fontResponse.setFont_url(font.getFontURL());
				fontResponse.setFont_sample(font.getFontSample());

				fontListResponse.getFonts().add(fontResponse);
			});

			apiResponse.setData(fontListResponse);
			apiResponse.setResponseMessage("Data retrieval is success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getFontList() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

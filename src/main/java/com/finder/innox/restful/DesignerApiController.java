package com.finder.innox.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.response.DesignerListResponse;
import com.finder.innox.response.DesignerResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class DesignerApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping(path = InnoxApiConstant.API_ARTWORK_DESIGNER)
	public String getDesignerList(HttpServletRequest request) {
		logger.info("getDesignerList() >> Start");
		String result = "";
		ProcessException pe = null;
		Response<DesignerListResponse> apiResponse = new Response<DesignerListResponse>();
		DesignerListResponse designerListResponse = new DesignerListResponse();

		List<UserDTO> desigerList = userService.getDesignerList();
		desigerList.forEach(designer -> {
			DesignerResponse designerResponse = new DesignerResponse();
			designerResponse.setDesigner_id(designer.getSeq());
			designerResponse.setDesigner_name(designer.getUserName());
			designerResponse.setDesigner_avatar(CommonUtil.prepareImagePath(designer.getAvatar(), request));

			designerListResponse.getDesigners().add(designerResponse);
		});

		apiResponse.setData(designerListResponse);
		apiResponse.setResponseMessage("Data retrival is success");

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		logger.info("getDesignerList() >> End");
		return result;
	}

}

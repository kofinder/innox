package com.finder.innox.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.StateDTO;
import com.finder.innox.core.services.StateService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.Response;
import com.finder.innox.response.StateListResponse;
import com.finder.innox.response.StateResponse;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class StateApiController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StateService stateService;

	@GetMapping(path = InnoxApiConstant.API_STATE_LIST)
	public String getStateList(HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		Response<StateListResponse> apiResponse = new Response<StateListResponse>();
		StateListResponse response = new StateListResponse();

		try {
			List<StateDTO> dtoList = stateService.getStateDataList(0);
			dtoList.forEach(state -> {
				response.getState_list().add(new StateResponse(state));
			});

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Data retrieval is success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getStateList() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}
}

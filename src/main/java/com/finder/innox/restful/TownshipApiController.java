package com.finder.innox.restful;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.TownshipDTO;
import com.finder.innox.core.services.TownshipService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.Response;
import com.finder.innox.response.TownshipListResponse;
import com.finder.innox.response.TownshipResponse;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class TownshipApiController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TownshipService townshipService;

	@GetMapping(path = InnoxApiConstant.API_TOWNSHIP_LIST, produces = "application/json; charset=utf-8")
	public String getTownshipListByState(@RequestParam(name = "state_id") Long state_id,
			HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		Response<TownshipListResponse> apiResponse = new Response<TownshipListResponse>();

		try {
			TownshipListResponse response = new TownshipListResponse();
			Long stateId = state_id != null ? Long.valueOf(state_id) : 0L;
			List<TownshipDTO> dtoList = townshipService.getTownshipListByState(stateId);

			dtoList.forEach(township -> {
				response.getTownship_list().add(new TownshipResponse(township));
			});

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Data retrieval is success");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTownshipListByState() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}

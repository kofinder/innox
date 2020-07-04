package com.finder.ecoshop.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.ecoshop.annotation.InnoxShopApi;
import com.finder.ecoshop.exception.ProcessException;
import com.finder.ecoshop.response.Response;
import com.finder.ecoshop.utils.JsonUtil;


@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class TodoRestApiController {

	private final ApplicationContext ctx;

	@Autowired
	public TodoRestApiController(final ApplicationContext ctx) {
		this.ctx = ctx;
	}

	@CrossOrigin("*")
	@GetMapping(path = InnoxApiConstant.API_EXECUTE_ENDPOINT)
	public String execute() {
		return "hello world";
	}
	
	@GetMapping(path = InnoxApiConstant.API_WELCOME)
	public String welcomeApi() {
		String result = "";
		Response<Object> apiResponse = new Response<>();
		ProcessException pe = null;
		
		apiResponse.setData("Hello World");
		apiResponse.setResponseMessage("Data retrieval is successful");
		
		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}
}

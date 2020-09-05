package com.finder.innox.restful;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finder.innox.annotation.InnoxShopApi;


@InnoxShopApi(apiPath = InnoxApiConstant.API_AUTH_RESOURCES_NAME)
public class TodoRestApiController {

	@RequestMapping(value = "/just", method = RequestMethod.GET)
	public ResponseEntity<?> justTest() throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication a = context.getAuthentication();
		System.out.println(a.getName());
		return ResponseEntity.ok("hello world");
	}
}

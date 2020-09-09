package com.finder.innox.restful;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finder.innox.JwtTokenUtil;
import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.Response;
import com.finder.innox.restful.model.JwtRequest;
import com.finder.innox.restful.model.JwtResponse;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class JwtAuthenticationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public String createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		String result = "";
		String token = "";
		Response<JwtResponse> apiResponse = new Response<JwtResponse>();
		ProcessException pe = null;
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

			if (userDetails == null) {
				return JsonUtil.prettyJSON(new JwtResponse(""));
			}

			token = jwtTokenUtil.generateToken(userDetails);

			apiResponse.setData(new JwtResponse(token));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createAuthenticationToken() >> " + e.getMessage(), e);

			if (e.getMessage().equals("INVALID_CREDENTIALS")) {
				pe = new ProcessException(ErrorType.INVALID_CREDENTIALS);
			} else if (e.getMessage().equals("USER_DISABLED")) {
				pe = new ProcessException(ErrorType.USER_DISABLED);
			}
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

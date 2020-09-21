package com.finder.innox.restful;

import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
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
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.request.JwtRequest;
import com.finder.innox.response.JwtResponse;
import com.finder.innox.response.Response;
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

	@RequestMapping(value = InnoxApiConstant.API_USER_LOGIN, method = RequestMethod.POST)
	public String createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse httpResponse) throws Exception {
		String result = "";
		Response<JwtResponse> apiResponse = new Response<JwtResponse>();
		ProcessException pe = null;
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

			if (userDetails != null) {

				UserDTO userDTO = userService.findByName(userDetails.getUsername(), 0);
				JwtResponse jwtResponse = new JwtResponse();
				jwtResponse.setUser_id(userDTO.getSeq());
				jwtResponse.setJwt_token(jwtTokenUtil.generateToken(userDetails));

				apiResponse.setData(jwtResponse);
			} else {
				httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("createAuthenticationToken() >> " + e.getMessage(), e);

			if (e.getMessage().equals("INVALID_CREDENTIALS")) {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.INVALID_CREDENTIALS, httpResponse);
			} else if (e.getMessage().equals("USER_DISABLED")) {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.USER_DISABLED, httpResponse);
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

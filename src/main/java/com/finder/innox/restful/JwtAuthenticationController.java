package com.finder.innox.restful;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.JwtTokenUtil;
import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.UserAddressDTO;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.UserAddressService;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.request.JwtRequest;
import com.finder.innox.response.Response;
import com.finder.innox.response.UserRegisterResponse;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;
import com.finder.innox.utils.UserRoleEnum;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class JwtAuthenticationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private UserAddressService addressService;

	@RequestMapping(value = InnoxApiConstant.API_USER_LOGIN, method = RequestMethod.POST)
	public String createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
		String result = "";
//		Response<JwtResponse> apiResponse = new Response<JwtResponse>();
		Response<UserRegisterResponse> apiResponse = new Response<UserRegisterResponse>();
		ProcessException pe = null;
		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

			if (userDetails != null) {

				UserDTO userDTO = userService.findByName(userDetails.getUsername(), 0);
				UserRegisterResponse response = new UserRegisterResponse();
				response.setUser_id(userDTO.getSeq());
				response.setUser_name(userDTO.getUserName());
				response.setEmail(userDTO.getEmail());
				response.setPhoneNo(userDTO.getPhoneNo());
				response.setUser_role_level(userDTO.getUserRoleLevel());
				response.setUser_role_level_text(UserRoleEnum.getDescByCode(userDTO.getUserRoleLevel()));
				response.setProfile_image(CommonUtil.prepareImagePath(userDTO.getAvatar(), httpRequest));
				response.setJwt_token(jwtTokenUtil.generateToken(userDetails));

				UserAddressDTO addressDTO = addressService.getUserAddressByUserId(userDTO.getSeq());
				if (addressDTO != null) {
					if (addressDTO.getStateDTO() != null) {
						response.setState_id(addressDTO.getStateDTO().getSeq());
						response.setState_name(addressDTO.getStateDTO().getName());
					} else {
						response.setState_name("-");
					}

					if (addressDTO.getTownshipDTO() != null) {
						response.setTownship_id(addressDTO.getTownshipDTO().getSeq());
						response.setTownship_name(addressDTO.getTownshipDTO().getTownshipName());
					} else {
						response.setTownship_name("-");
					}

					response.setDetail_address(addressDTO.getDetailAddress());
				} else {
					response.setState_name("-");
					response.setTownship_name("-");
					response.setDetail_address("");
				}

//				JwtResponse jwtResponse = new JwtResponse();
//				jwtResponse.setUser_id(userDTO.getSeq());
//				jwtResponse.setJwt_token(jwtTokenUtil.generateToken(userDetails));

				apiResponse.setData(response);
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

	@GetMapping(path = InnoxApiConstant.API_JWT_TOKEN_EXPIRE, produces = "application/json; charset=utf-8")
	public String checkJwtTokenExpire(@RequestParam(name = "jwt_token") String jwtToken, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<UserRegisterResponse> apiResponse = new Response<UserRegisterResponse>();

		try {
			if (CommonUtil.isEmpty(jwtToken)) {
				errorList.add(new FieldError(FieldCode.JWT_TOKEN_REQUIRED.getCode(),
						ErrorMessage.JWT_TOKEN_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				UserRegisterResponse response = new UserRegisterResponse();
				boolean isJwtTokenExpire = jwtTokenUtil.isTokenExpired(jwtToken);
				logger.info("checkJwtTokenExpire() >> jwt token expire : " + isJwtTokenExpire);
				response.setValid_jwt_token(isJwtTokenExpire);
				apiResponse.setData(response);
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}
		} catch (ExpiredJwtException ex) {
			logger.info("#### checkJwtTokenExpire() >> " + ex.getMessage());
			logger.info("#### checkJwtTokenExpire() >> WT Token has been expired!");
			UserRegisterResponse response = new UserRegisterResponse();
			response.setValid_jwt_token(true);
			apiResponse.setData(response);
		} catch (SignatureException sg) {
			logger.info("#### checkJwtTokenExpire() >> " + sg.getMessage());
			logger.info("#### checkJwtTokenExpire() >> WT Token does not match!");
			UserRegisterResponse response = new UserRegisterResponse();
			response.setValid_jwt_token(true);
			apiResponse.setData(response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("#### checkJwtTokenExpire() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
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

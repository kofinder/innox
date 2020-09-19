package com.finder.innox.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finder.innox.JwtTokenUtil;
import com.finder.innox.core.dto.UserAddressDTO;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.UserAddressService;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.request.UserRegisterRequest;
import com.finder.innox.response.Response;
import com.finder.innox.response.UserRegisterResponse;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;
import com.finder.innox.utils.UserRoleEnum;

@RestController
public class UserApiController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserAddressService addressService;

	@PostMapping(path = InnoxApiConstant.API_RESOURCES_NAME + InnoxApiConstant.API_USER_REGISTER)
	public String userRegister(@RequestBody UserRegisterRequest registerRequest, HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<UserRegisterResponse> apiResponse = new Response<UserRegisterResponse>();

		try {

			isValidUserRegisterData(registerRequest, errorList);

			if (errorList.size() == 0) {

				UserDTO userDTO = userService.userRegister(registerRequest);
				if (userDTO != null) {
					UserRegisterResponse response = new UserRegisterResponse();
					response.setUser_id(userDTO.getSeq());
					response.setUser_name(userDTO.getUserName());
					response.setEmail(userDTO.getEmail());
					response.setPhoneNo(userDTO.getPhoneNo());
					response.setUser_role_level(userDTO.getUserRoleLevel());
					response.setUser_role_level_text(UserRoleEnum.getDescByCode(userDTO.getUserRoleLevel()));
					response.setProfile_image(CommonUtil.prepareImagePath(userDTO.getAvatar(), request));

					UserDetails userDetails = userService.loadUserByUsername(response.getUser_name());

					if (userDetails == null) {
						response.setJwt_token("");
					} else {
						response.setJwt_token(jwtTokenUtil.generateToken(userDetails));
					}

					apiResponse.setData(response);
					apiResponse.setResponseMessage("User register is success");
				}

			} else {
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
				pe.setFieldErrorList(errorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userRegister() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME + InnoxApiConstant.API_USER)
	public String getUserProfileData(@RequestParam(name = "user_id") long user_id, HttpServletRequest request) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<UserRegisterResponse> apiResponse = new Response<UserRegisterResponse>();

		try {

			if (user_id <= 0) {
				errorList.add(new FieldError(FieldCode.CUSTOMER_ID.getCode(),
						ErrorMessage.CUSTOMER_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				UserDTO userDTO = userService.getUserById(user_id);
				if (userDTO != null) {
					UserRegisterResponse response = new UserRegisterResponse();
					response.setUser_id(userDTO.getSeq());
					response.setUser_name(userDTO.getUserName());
					response.setEmail(userDTO.getEmail());
					response.setPhoneNo(userDTO.getPhoneNo());
					response.setUser_role_level(userDTO.getUserRoleLevel());
					response.setUser_role_level_text(UserRoleEnum.getDescByCode(userDTO.getUserRoleLevel()));
					response.setProfile_image(CommonUtil.prepareImagePath(userDTO.getAvatar(), request));

					UserDetails userDetails = userService.loadUserByUsername(response.getUser_name());

					if (userDetails == null) {
						response.setJwt_token("");
					} else {
						response.setJwt_token(jwtTokenUtil.generateToken(userDetails));
					}

					UserAddressDTO addressDTO = addressService.getUserAddressByUserId(userDTO.getSeq());
					if (addressDTO != null) {
						response.setState_id(addressDTO.getStateDTO().getSeq());
						response.setState_name(addressDTO.getStateDTO().getName());
						response.setTownship_id(addressDTO.getTownshipDTO().getSeq());
						response.setTownship_name(addressDTO.getTownshipDTO().getTownshipName());
						response.setDetail_address(addressDTO.getDetailAddress());
					}

					apiResponse.setData(response);
					apiResponse.setResponseMessage("Data retrieval is success!");
				} else {
					pe = new ProcessException(ErrorType.INVALID_DATA);
				}
			} else {
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserProfileData() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@PutMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME + InnoxApiConstant.API_USER)
	public String updateUserProfile(@RequestBody UserRegisterRequest profileUpdateRequest, HttpServletRequest request) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<String> apiResponse = new Response<String>();

		try {

			if (CommonUtil.isEmpty(request.getHeader("user_id")) || Long.valueOf(request.getHeader("user_id")) <= 0) {
				errorList.add(new FieldError(FieldCode.CUSTOMER_ID.getCode(),
						ErrorMessage.CUSTOMER_ID_REQUIRED.getMessage()));
			} else {
				profileUpdateRequest.setUser_id(Long.valueOf(request.getHeader("user_id")));
			}

			isValidProfileUpdateData(profileUpdateRequest, errorList);

			if (errorList.size() == 0) {
				UserDTO userDTO = userService.userProfileUpdate(profileUpdateRequest);
				if (userDTO != null) {
					apiResponse.setResponseMessage("User profile update is success!");
				} else {
					pe = new ProcessException(ErrorType.INVALID_DATA);
				}
			} else {
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateUserProfile() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	private void isValidUserRegisterData(UserRegisterRequest registerRequest, List<FieldError> errorList) {
		if (CommonUtil.isEmpty(registerRequest.getName())) {
			errorList.add(new FieldError(FieldCode.USER_NAME.getCode(), ErrorMessage.USER_NAME_REQUIRED.getMessage()));
		}

		if (registerRequest.getUser_role() <= 0) {
			errorList.add(new FieldError(FieldCode.USER_ROLE.getCode(), ErrorMessage.USER_ROLE_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(registerRequest.getPhoneNo())) {
			errorList.add(new FieldError(FieldCode.PHONE_NO.getCode(), ErrorMessage.PHONE_NO_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(UserRoleEnum.getDescByCode(registerRequest.getUser_role()))) {
			errorList.add(new FieldError(FieldCode.USER_ROLE.getCode(), ErrorMessage.USER_ROLE_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(registerRequest.getPassword())) {
			errorList.add(new FieldError(FieldCode.PASSWORD.getCode(), ErrorMessage.PASSWORD_REQUIRED.getMessage()));
		} else {
			if (!registerRequest.getPassword().equals(registerRequest.getConfirm_password())) {
				errorList.add(
						new FieldError(FieldCode.PASSWORD.getCode(), ErrorMessage.PASSWORD_NOT_MATCH.getMessage()));
			}
		}

		if (!CommonUtil.isEmpty(registerRequest.getName()) && registerRequest.getUser_role() > 0) {
			if (userService.isUserNameAlreadExist(registerRequest.getName(),
					UserRoleEnum.getCodeByCode(registerRequest.getUser_role()), 0)) {
				errorList.add(
						new FieldError(FieldCode.DUPLICATE_NAME.getCode(), ErrorMessage.DUPLICATE_NAME.getMessage()));
			}
		}

		// TODO user name duplicated phone no check
	}

	private void isValidProfileUpdateData(UserRegisterRequest profileUpdateRequest, List<FieldError> errorList) {

		if (CommonUtil.isEmpty(profileUpdateRequest.getName())) {
			errorList.add(new FieldError(FieldCode.USER_NAME.getCode(), ErrorMessage.USER_NAME_REQUIRED.getMessage()));
		}

		if (profileUpdateRequest.getUser_role() <= 0) {
			errorList.add(new FieldError(FieldCode.USER_ROLE.getCode(), ErrorMessage.USER_ROLE_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(profileUpdateRequest.getPhoneNo())) {
			errorList.add(new FieldError(FieldCode.PHONE_NO.getCode(), ErrorMessage.PHONE_NO_REQUIRED.getMessage()));
		}

		if (profileUpdateRequest.getState_id() <= 0) {
			errorList.add(new FieldError(FieldCode.STATE_ID.getCode(), ErrorMessage.STATE_ID_REQUIRED.getMessage()));
		}

		if (profileUpdateRequest.getTownship_id() <= 0) {
			errorList.add(new FieldError(FieldCode.TOWNSHIP.getCode(), ErrorMessage.TOWNSHIP_ID_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(profileUpdateRequest.getDetail_address())) {
			errorList.add(new FieldError(FieldCode.ADDRESS.getCode(), ErrorMessage.ADDRESS_REQUIRED.getMessage()));
		}

		if (profileUpdateRequest.getUser_id() > 0 && !CommonUtil.isEmpty(profileUpdateRequest.getName())
				&& profileUpdateRequest.getUser_role() > 0) {
			if (userService.isUserNameAlreadExist(profileUpdateRequest.getName(),
					UserRoleEnum.getCodeByCode(profileUpdateRequest.getUser_role()),
					profileUpdateRequest.getUser_id())) {
				errorList.add(
						new FieldError(FieldCode.DUPLICATE_NAME.getCode(), ErrorMessage.DUPLICATE_NAME.getMessage()));
			}
		}

	}
}

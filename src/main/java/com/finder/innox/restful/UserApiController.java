package com.finder.innox.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
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
import com.finder.innox.request.DeviceTokenUpdateRequest;
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

	@PostMapping(path = InnoxApiConstant.API_RESOURCES_NAME
			+ InnoxApiConstant.API_USER_REGISTER, produces = "application/json; charset=utf-8")
	public String userRegister(@RequestBody UserRegisterRequest registerRequest, HttpServletRequest request,
			HttpServletResponse httpResponse) {
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
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("userRegister() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME
			+ InnoxApiConstant.API_USER, produces = "application/json; charset=utf-8")
	public String getUserProfileData(@RequestParam(name = "user_id") long user_id, HttpServletRequest request,
			HttpServletResponse httpResponse) {
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

					apiResponse.setData(response);
					apiResponse.setResponseMessage("Data retrieval is success!");
				} else {
					httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
				}
			} else {
				httpResponse.setStatus((HttpStatus.SC_BAD_REQUEST));
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getUserProfileData() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@PutMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME
			+ InnoxApiConstant.API_USER, produces = "application/json; charset=utf-8")
	public String updateUserProfile(@RequestBody UserRegisterRequest profileUpdateRequest, HttpServletRequest request,
			HttpServletResponse httpResponse) {
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
					httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateUserProfile() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@PutMapping(path = InnoxApiConstant.API_AUTH_RESOURCES_NAME
			+ InnoxApiConstant.API_DEVICE_TOKEN_UPDATE, produces = "application/json; charset=utf-8")
	public String updateDeviceToken(@RequestBody DeviceTokenUpdateRequest request, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<String> apiResponse = new Response<String>();
		try {

			if (CommonUtil.isEmpty(httpRequest.getHeader("user_id"))
					|| Long.valueOf(httpRequest.getHeader("user_id")) <= 0) {
				errorList.add(new FieldError(FieldCode.CUSTOMER_ID.getCode(),
						ErrorMessage.CUSTOMER_ID_REQUIRED.getMessage()));
			} else if (CommonUtil.isEmpty(request.getDevice_token())) {
				errorList.add(new FieldError(FieldCode.DEVICE_TOKEN.getCode(),
						ErrorMessage.DEVICE_TOKEN_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				request.setUser_id(Long.valueOf(httpRequest.getHeader("user_id")));
				int updateCount = userService.updateDeviceToken(request);

				if (updateCount == 1) {
					apiResponse.setResponseMessage("Device token update is success!");
				} else {
					httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateDeviceToken() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	private void isValidUserRegisterData(UserRegisterRequest registerRequest, List<FieldError> errorList) {
		if (CommonUtil.isEmpty(registerRequest.getName())) {
			errorList.add(new FieldError(FieldCode.USER_NAME.getCode(), ErrorMessage.USER_NAME_REQUIRED.getMessage()));
		}

		if (registerRequest.getDevice_type() <= 0) {
			errorList.add(
					new FieldError(FieldCode.DEVICE_TYPE.getCode(), ErrorMessage.DEVICE_TYPE_REQUIRED.getMessage()));
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

		// user name duplicated check
		if (!CommonUtil.isEmpty(registerRequest.getName()) && registerRequest.getUser_role() > 0) {
			if (userService.isUserNameAlreadExist(registerRequest.getName(),
					UserRoleEnum.getCodeByCode(registerRequest.getUser_role()), 0)) {
				errorList.add(
						new FieldError(FieldCode.DUPLICATE_NAME.getCode(), ErrorMessage.DUPLICATE_NAME.getMessage()));
			}
		}

		// user phone no duplicate check
		if (!CommonUtil.isEmpty(registerRequest.getPhoneNo())
				&& userService.findByPhoneNo(registerRequest.getPhoneNo(), 0) != null) {
			errorList.add(new FieldError(FieldCode.DUPLICATED_PHONE_NO.getCode(),
					ErrorMessage.DUPLICATED_PHONE_NO.getMessage()));
		}
	}

	private void isValidProfileUpdateData(UserRegisterRequest profileUpdateRequest, List<FieldError> errorList) {

		if (CommonUtil.isEmpty(profileUpdateRequest.getName())) {
			errorList.add(new FieldError(FieldCode.USER_NAME.getCode(), ErrorMessage.USER_NAME_REQUIRED.getMessage()));
		}

//		if (profileUpdateRequest.getUser_role() <= 0) {
//			errorList.add(new FieldError(FieldCode.USER_ROLE.getCode(), ErrorMessage.USER_ROLE_REQUIRED.getMessage()));
//		}

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

		// user name duplicated check
		if (profileUpdateRequest.getUser_id() > 0 && !CommonUtil.isEmpty(profileUpdateRequest.getName())
				&& profileUpdateRequest.getUser_role() > 0) {
			if (userService.isUserNameAlreadExist(profileUpdateRequest.getName(),
					UserRoleEnum.getCodeByCode(profileUpdateRequest.getUser_role()),
					profileUpdateRequest.getUser_id())) {
				errorList.add(
						new FieldError(FieldCode.DUPLICATE_NAME.getCode(), ErrorMessage.DUPLICATE_NAME.getMessage()));
			}
		}

		// user phone no duplicate check
		if (!CommonUtil.isEmpty(profileUpdateRequest.getPhoneNo()) && userService
				.findByPhoneNo(profileUpdateRequest.getPhoneNo(), profileUpdateRequest.getUser_id()) != null) {
			errorList.add(new FieldError(FieldCode.DUPLICATED_PHONE_NO.getCode(),
					ErrorMessage.DUPLICATED_PHONE_NO.getMessage()));
		}

	}
}

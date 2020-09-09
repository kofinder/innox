package com.finder.innox.restful;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.UserDTO;
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

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class UserApiController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@PostMapping(path = InnoxApiConstant.API_USER_REGISTER)
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

	private void isValidUserRegisterData(UserRegisterRequest registerRequest, List<FieldError> errorList) {
		if (CommonUtil.isEmpty(registerRequest.getName())) {
			errorList.add(new FieldError(FieldCode.USER_NAME.getCode(), ErrorMessage.USER_NAME_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(registerRequest.getPhoneNo())) {
			errorList.add(new FieldError(FieldCode.PHONE_NO.getCode(), ErrorMessage.PHONE_NO_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(registerRequest.getPassword())) {
			errorList.add(new FieldError(FieldCode.PASSWORD.getCode(), ErrorMessage.PASSWORD_REQUIRED.getMessage()));
		} else {
			if (!registerRequest.getPassword().equals(registerRequest.getConfirm_password())) {
				errorList.add(
						new FieldError(FieldCode.PASSWORD.getCode(), ErrorMessage.PASSWORD_NOT_MATCH.getMessage()));
			}
		}

		// TODO user name duplicated check
	}
}

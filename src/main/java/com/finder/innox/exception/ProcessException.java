package com.finder.innox.exception;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

import com.finder.innox.utils.FieldError;

public class ProcessException extends Exception {

	private static final long serialVersionUID = 6371361798365884673L;

	private ErrorType errorType;

	private List<FieldError> fieldErrorList;

	public ProcessException(ErrorType errorType, HttpServletResponse httpResponse) {
		super(errorType.getDescription());
		this.setErrorType(errorType);
		
		if(errorType.getCode() == ErrorType.GENERAL.code) {
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		}else if(errorType.getCode() == ErrorType.MULTIPLE_ERROR.code){
			httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
	}

	public enum ErrorType {
		UNAUTHORIZED("401", "UnAuthorized Request"),
		INVALID_CREDENTIALS("400", "Invalid Credentials"),
		USER_DISABLED("400", "User account disabled"),
		GENERAL("-1", "General application exception occurred while processing client request."),
		LOGIN_FAIL("-1", "Username or Password is incorrect."), INVALID_DATA("-1", "Invalid Data"),
		MULTIPLE_ERROR("1000", ""), INVALID_SESSION("1001", "Invalid Session!"),
		PHONE_DUPLICATE("1002", "Someone’s already using that Phone No."),
		EMAIL_DUPLICATE("1003", "Someone’s already using that Email"),
		NAME_DUPLICATE("1004", "Someone's already using that name");

		private ErrorType(String iCode, String idescription) {
			code = iCode;
			description = idescription;
		}

		private final String code;
		private String description;

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public List<FieldError> getFieldErrorList() {
		return fieldErrorList;
	}

	public void setFieldErrorList(List<FieldError> fieldErrorList) {
		this.fieldErrorList = fieldErrorList;
	}

}

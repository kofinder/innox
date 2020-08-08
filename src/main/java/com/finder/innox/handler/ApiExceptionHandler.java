package com.finder.innox.handler;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.finder.innox.exception.CommonApiException;
import com.finder.innox.utils.JsonUtil;

@RestController
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ 
		CommonApiException.class,
		ResourceAccessException.class,
		InvocationTargetException.class
	})
	public ResponseEntity<?> handleThrowableException(final CommonApiException cause) {
		final CommonApiException ex = getException(cause.getCause());
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", ex.getStatus());
		result.put("developerMessage", ex.getDeveloperMessage());
		result.put("userMessage", ex.getUserMessage());
		return new ResponseEntity<Object>(JsonUtil.toJSON(result), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private CommonApiException getException(Throwable throwable) {
		Exception ex = (Exception) throwable;
		CommonApiException exception = new CommonApiException();
		exception.setDeveloperMessage(ex.getMessage());
		exception.setUserMessage("please contact administrator!");
		return exception;
	}
}

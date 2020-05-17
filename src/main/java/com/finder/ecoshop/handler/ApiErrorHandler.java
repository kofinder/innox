package com.finder.ecoshop.handler;

import java.io.IOException;
import java.net.NoRouteToHostException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;

import com.finder.ecoshop.exception.CommonApiException;


public class ApiErrorHandler implements ResponseErrorHandler {

	private final ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

	@Override
	public void handleError(ClientHttpResponse res) throws IOException {
		try {
			errorHandler.handleError(res);
		} catch (ResourceAccessException | HttpClientErrorException | HttpServerErrorException | NoRouteToHostException ex) {
			throw new CommonApiException(ex.getMessage(), ex.getCause());
		} 
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return errorHandler.hasError(response);
	}
}

package com.finder.innox.logging;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finder.innox.utils.JsonUtil;


public class HttpRestClientApiLogging implements ClientHttpRequestInterceptor {

	protected Logger requestLogger = LoggerFactory.getLogger("restclient.message.tracing.sent");
	protected Logger uriLogger = LoggerFactory.getLogger("restclient.message.tracing.uri");
	protected Logger responseLogger = LoggerFactory.getLogger("restclient.message.tracing.received");

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		loggerRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		loggerResponse(request, response);

		return response;
	}

	private void loggerRequest(HttpRequest request, byte[] body) throws IOException {
		requestLogger.info(request.getURI() + JsonUtil.prettyJSON(new ObjectMapper().readValue(new String(body, "UTF-8"), Object.class)));
	}

	private void loggerResponse(HttpRequest request, ClientHttpResponse response) throws IOException {
		responseLogger.info(request.getURI() + JsonUtil.prettyJSON(new ObjectMapper()
				.readValue(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()), Object.class)));
	}

}

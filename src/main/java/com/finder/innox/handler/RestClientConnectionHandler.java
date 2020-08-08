package com.finder.innox.handler;

import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.finder.innox.logging.HttpRestClientApiLogging;


@Component
public class RestClientConnectionHandler {

	private RestTemplate rest;
	private HttpHeaders headers;
	private HttpStatus status;
	private int statusCode;

	@Value(value = "${rest.connectTimeout:0}")
	private int connectTimeout;
	@Value(value = "${rest.readTimeout:0}")
	private int readTimeout;

	@SuppressWarnings("deprecation")
	public RestClientConnectionHandler() {
		this.rest = new RestTemplate();
		this.headers = new HttpHeaders();

		// set header value
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(singletonList(APPLICATION_JSON_UTF8));

		// set intercepter for logging
		rest.setInterceptors(Collections.singletonList(new HttpRestClientApiLogging()));

		// add message converter with utf8
		rest.getMessageConverters().add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

		// set request factory
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(connectTimeout);
		clientHttpRequestFactory.setReadTimeout(readTimeout);
		rest.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory));

		// set error handler
		rest.setErrorHandler(new ApiErrorHandler());
	}

	public String get(String uri, String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		this.setStatusCode(responseEntity.getStatusCodeValue());
		this.setStatus(responseEntity.getStatusCode());
		return responseEntity.getBody();
	}

	public String post(String uri, String json) throws RestClientException {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		this.setStatusCode(responseEntity.getStatusCodeValue());
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public String put(String uri, String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.PUT, requestEntity, String.class);
		this.setStatusCode(responseEntity.getStatusCodeValue());
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public String delete(String uri, String json) {
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> responseEntity = rest.exchange(uri, HttpMethod.DELETE, requestEntity, String.class);
		this.setStatusCode(responseEntity.getStatusCodeValue());
		this.setStatus(responseEntity.getStatusCode());

		return responseEntity.getBody();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}

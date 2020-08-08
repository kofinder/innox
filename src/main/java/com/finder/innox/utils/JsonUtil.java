package com.finder.innox.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonUtil {

	public static String toJSON(Object object) {
		final ObjectMapper jsonMapper = new ObjectMapper();
		try {
			return jsonMapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String prettyJSON(Object object) {
		final ObjectMapper jsonMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

		try {
			return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public static String formatJsonResponse(@SuppressWarnings("rawtypes") Response response, ProcessException pe) {
		Gson gson = new GsonBuilder().create();
		if(pe != null) {
			response.setResponseCode(pe.getErrorType().getCode());
			if(ErrorType.MULTIPLE_ERROR.getCode().equalsIgnoreCase(pe.getErrorType().getCode())) {
				response.setErrorList(pe.getFieldErrorList());
			}
			response.setResponseMessage(pe.getMessage());
		}
		return gson.toJson(response);
	}
}

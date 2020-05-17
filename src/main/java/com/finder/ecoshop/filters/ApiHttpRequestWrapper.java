package com.finder.ecoshop.filters;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;



public class ApiHttpRequestWrapper extends HttpServletRequestWrapper {
	private final byte[] payload;

	public ApiHttpRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
	//	IOUtils.copy(request.getInputStream(), bos);
		payload = bos.toByteArray();
	}

	public byte[] getPayload() {
		return payload;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getPayload());
		return new ServletInputStream() {
			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}

	public byte[] getContentAsByteArray() throws IOException {
		return getPayload();
	}

	public Map<String, String> getParameters() {
		return getParameterMap().entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> {
			String[] values = e.getValue();
			return values.length > 0 ? values[0] : "[EMPTY]";
		}));
	}

	public Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<>(0);
		Enumeration<String> headerNames = getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			if (headerName != null) {
				headers.put(headerName, getHeader(headerName));
			}
		}
		return headers;
	}

}
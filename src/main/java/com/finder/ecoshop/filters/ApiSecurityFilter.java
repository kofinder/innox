package com.finder.ecoshop.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class ApiSecurityFilter extends OncePerRequestFilter {

	protected Logger requestLogger;
	protected Logger responseLogger;

	public ApiSecurityFilter() {
		this(LoggerFactory.getLogger("topup.mobile.message.tracing.sent"),
				LoggerFactory.getLogger("topup.mobile.message.tracing.received"));
	}

	public ApiSecurityFilter(Logger requestLogger, Logger responseLogger) {
		this.requestLogger = requestLogger;
		this.responseLogger = responseLogger;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		filterChain.doFilter(request, response);
	}

}

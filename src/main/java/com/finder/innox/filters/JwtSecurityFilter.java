package com.finder.innox.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.finder.innox.JwtTokenUtil;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.Response;
import com.finder.innox.utils.JsonUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	protected Logger requestLogger;
	protected Logger responseLogger;

	private AntPathMatcher pathMatcher = new AntPathMatcher();

	private static List<String> IGNORE_URLS = Arrays.asList("/", "/banner_setup", "/brand_setup", "/category_search",
			"/category_setup", "/color_setup", "/custom_product_search", "/pcustom_product_setup", "/dashboard",
			"/error", "/font_setup", "/login", "product_search", "product_setup", "/register", "/size_setup",
			"/state_setup", "/township_setup", "/welcome", "/zone_setup", "/saveUser", "/register");

	public JwtSecurityFilter() {
		this(LoggerFactory.getLogger("innox.mobile.message.tracing.sent"),
				LoggerFactory.getLogger("innox.mobile.message.tracing.received"));
	}

	public JwtSecurityFilter(Logger requestLogger, Logger responseLogger) {
		this.requestLogger = requestLogger;
		this.responseLogger = responseLogger;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + request.getRequestURI());
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				if (jwtTokenUtil.isTokenExpired(jwtToken)) {
					logger.info(">>> Token Expired >>>");
					createResposenData(response, HttpStatus.UNAUTHORIZED, "Bearer token is expire!",
							ErrorType.UNAUTHORIZED);
				} else {
					logger.info(">>> Token Not Expire >>>");
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				}

			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			createResposenData(response, HttpStatus.UNAUTHORIZED, "Bearer token is required!", ErrorType.UNAUTHORIZED);
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the Spring Security
				// Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				logger.info(">>> User name not valid with bearer token >>>");
				createResposenData(response, HttpStatus.UNAUTHORIZED, "User name not valid with bearer token",
						ErrorType.UNAUTHORIZED);
			}
		} else {
			logger.info(">>> User name not found >>>");
			createResposenData(response, HttpStatus.UNAUTHORIZED, "User name not found!", ErrorType.UNAUTHORIZED);
		}

		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if (IGNORE_URLS.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()))
				|| request.getRequestURI().endsWith(".html") || request.getRequestURI().startsWith("/resources")
				|| request.getRequestURI().startsWith("/images") || request.getRequestURI().endsWith(".ico")) {
			return true;
		}
		return false;
	}

	public boolean isIgnoreUrl(String uri) {
		for (String ignoreUrl : IGNORE_URLS) {
			if (uri.endsWith(ignoreUrl)) {
				return true;
			}
		}
		return false;
	}

	private void createResposenData(HttpServletResponse response, HttpStatus responseStatus, String errorMsg,
			ErrorType errorType) throws IOException {
		logger.warn("createResposenData() >> " + responseStatus);
		String result = "";
		Response<String> apiResponse = new Response<String>();
		apiResponse.setData(errorMsg);
		ProcessException pe = new ProcessException(errorType);
		result = JsonUtil.formatJsonResponse(apiResponse, pe);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(result);
		out.flush();
	}
}

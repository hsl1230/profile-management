package com.telus.core.errorhandling.config;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telus.core.errorhandling.PlatformErrorCode;
import com.telus.core.errorhandling.errorhandler.ExceptionProcessor;
import com.telus.core.errorhandling.exception.BadRequestException;
import com.telus.core.errorhandling.resource.ErrorResultResource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Priority(value = Integer.MIN_VALUE)
@ConditionalOnClass({
		OncePerRequestFilter.class,
		Servlet.class
})
public class DefaultExceptionHandlingFilter extends OncePerRequestFilter {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private final ExceptionProcessor exceptionProcessor;

	public DefaultExceptionHandlingFilter(ExceptionProcessor exceptionProcessor) {
		this.exceptionProcessor = exceptionProcessor;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		try {
			filterChain.doFilter(request, response);
		} catch (RuntimeException ex) {
			if (ex.getCause() instanceof JsonParseException) {
				BadRequestException bre = new BadRequestException(PlatformErrorCode.REQUEST_NOT_PARSABLE, ex);
				handleException(bre, response);
			} else {
				handleException(ex, response);
			}
		}

	}

	private void handleException(RuntimeException ex, HttpServletResponse response) {
		ResponseEntity<ErrorResultResource> responseEntity = exceptionProcessor.process(ex);

		copyHeadersToResponse(responseEntity.getHeaders(), response);
		response.setStatus(responseEntity.getStatusCodeValue());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		try {
			String bodyAsJson = objectMapper.writeValueAsString(responseEntity.getBody());
			response.getWriter().write(bodyAsJson);
		} catch (IOException e) {
			String message = String.format("Error setting http response '%s'", ex.getMessage());
			logger.error(message, ex);
		}
	}

	private static void copyHeadersToResponse(HttpHeaders headers, HttpServletResponse response) {
		headers.forEach((name, values) -> values.forEach(value -> response.addHeader(name, value)));
	}
}

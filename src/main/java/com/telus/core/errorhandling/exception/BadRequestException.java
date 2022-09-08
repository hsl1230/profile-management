package com.telus.core.errorhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.telus.core.errorhandling.ErrorCode;

@ResponseStatus(
    value = HttpStatus.BAD_REQUEST,
    reason = "An invalid request was submitted."
)
public class BadRequestException extends PlatformException {
	public BadRequestException(ErrorCode errorCode, Throwable cause) {
		super(HttpStatus.BAD_REQUEST, errorCode, cause);
	}	
}

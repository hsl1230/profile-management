package com.telus.core.errorhandling.errorhandler;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import com.telus.core.errorhandling.ErrorCode;
import com.telus.core.errorhandling.exception.PlatformException;
import com.telus.core.errorhandling.resource.ErrorResultResource;

public abstract class AbstractExceptionHandler<T extends Exception> extends ExceptionHandler {
    @Override
    public Class<? extends Throwable> exceptionClass() {
        Type genericSuperclass = getClass().getGenericSuperclass();

        final Type t = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
        return (Class<? extends Throwable>) t;
    }

    @Override
    public ResponseEntity<ErrorResultResource> handleException(Exception exception) {
        T ex = (T) exception;
        return processException(ex);
    }

    protected abstract ErrorCode errorCode(T exception);

    protected abstract HttpStatus httpStatus(T exception);

    protected List<FieldError> fieldErrors(T exception) {
        return Collections.emptyList();
    }

    protected HttpHeaders httpHeaders(T exception) {
        return HttpHeaders.EMPTY;
    }

    protected ResponseEntity<ErrorResultResource> processException(T exception) {
        logger.error("Caught Exception", exception);

        PlatformException platformException = new PlatformException(
                httpStatus(exception),
                errorCode(exception),
                fieldErrors(exception),
                exception);

        processPlatformException(platformException, exception);

        return new ResponseEntityBuilder()
                .platformException(platformException)
                .headers(httpHeaders(exception))
                .build();
    }

    protected void processPlatformException(PlatformException platformException, T exception) {
    }
}

package com.nomi.rest.error;

import com.google.common.base.Throwables;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorInfoDto> handleError(Exception e) {
        ErrorInfoDto
            error =
            new ErrorInfoDto(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Throwables.getStackTraceAsString(e));

        return new ResponseEntity<>(error, createExceptionDefaultHttpHeaders(), HttpStatus.BAD_REQUEST);
    }

/*
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfoDto> handleNotFoundError(ResourceNotFoundException e) {
        ErrorInfoDto error =
            new ErrorInfoDto(HttpStatus.NOT_FOUND.value(),
                StringUtils.defaultString(e.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase()));

        return new ResponseEntity<>(error, createExceptionDefaultHttpHeaders(), HttpStatus.NOT_FOUND);
    }
*/

    private HttpHeaders createExceptionDefaultHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return httpHeaders;
    }
}

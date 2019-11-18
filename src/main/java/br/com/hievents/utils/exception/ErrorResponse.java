package br.com.hievents.utils.exception;

import org.springframework.http.HttpStatus;

public interface ErrorResponse {
    HttpStatus getHttpStatus();
    String getMessage();
    String getCode();
}

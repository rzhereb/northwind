package com.northwind.northwindrestapi.controller;

import com.northwind.northwindrestapi.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class ErrorController {

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(final DataIntegrityViolationException  e) {
    return createError(e.getRootCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST);
  }

  private static ResponseEntity<ErrorResponse> createError(final String message, final HttpStatus statusCode) {
    return new ResponseEntity<>(new ErrorResponse(message), statusCode);
  }
}

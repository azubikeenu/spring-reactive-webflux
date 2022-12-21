package com.azubike.ellipsis.spring_webflux_test.exceptions;

import org.springframework.http.HttpStatus;

public class ProductException extends Exception {
  private HttpStatus status;

  public ProductException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public ProductException(String message, Throwable cause) {
    super(message, cause);
  }
}

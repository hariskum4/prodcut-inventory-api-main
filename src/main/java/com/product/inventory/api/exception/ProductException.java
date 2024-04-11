package com.product.inventory.api.exception;

import org.springframework.http.HttpStatus;

public class ProductException extends RuntimeException {
	
  private final HttpStatus httpStatus;

  public ProductException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
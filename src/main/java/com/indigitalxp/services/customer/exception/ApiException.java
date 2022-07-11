package com.indigitalxp.services.customer.exception;

public class ApiException extends RuntimeException {
  private final String errorCode;
  private final String errorMessage;

  public ApiException(String errorCode, String errorMessage) {
    super();
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}

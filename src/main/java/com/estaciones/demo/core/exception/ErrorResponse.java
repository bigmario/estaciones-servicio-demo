package com.estaciones.demo.core.exception;

import lombok.Data;

@Data
public class ErrorResponse {
  private final String message;

  @Override
  public String toString() {
    return "{\"message\":\"" + message + "\"}";
  }
}

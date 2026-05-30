package com.apps.pkador666.quality_api.model;

import java.util.List;

public class ApiResponse<T> {
  private T data; // Lista de datos (uno o varios)
  private int status; // HTTP status code
  private String message; // Mensaje descriptivo
  private ErrorDetail error; // Detalle de error
  private ValidationError validation; // Detalle de validación

  // Constructores privados, usamos factory methods
  private ApiResponse() {
  }

  // ÉXITO: Un solo item
  public static <T> ApiResponse<T> success(T item, String message) {
    ApiResponse<T> response = new ApiResponse<>();
    response.data = item; // Lista con un elemento
    response.status = 200;
    response.message = message;
    response.error = ErrorDetail.none();
    response.validation = ValidationError.none();
    return response;
  }

  // ÉXITO: Lista de items
  // public static <T> ApiResponse<T> success(List<T> items, String message) {
  //   ApiResponse<T> response = new ApiResponse<>();
  //   response.data = (T) items;
  //   response.status = 200;
  //   response.message = message;
  //   response.error = ErrorDetail.none();
  //   response.validation = ValidationError.none();
  //   return response;
  // }

  // ERROR del servidor (500, etc.)
  public static <T> ApiResponse<T> error(int status, String message, String errorMessage) {
    ApiResponse<T> response = new ApiResponse<>();
    response.data = null;
    response.status = status;
    response.message = message;
    response.error = ErrorDetail.of(errorMessage);
    response.validation = ValidationError.none();
    return response;
  }

  // ERROR de validación (400, datos inválidos)
  public static <T> ApiResponse<T> validation(String message, String validationMessage) {
    ApiResponse<T> response = new ApiResponse<>();
    response.data = null;
    response.status = 400;
    response.message = message;
    response.error = ErrorDetail.none();
    response.validation = ValidationError.of(validationMessage);
    return response;
  }

  // Getters y Setters
  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorDetail getError() {
    return error;
  }

  public void setError(ErrorDetail error) {
    this.error = error;
  }

  public ValidationError getValidation() {
    return validation;
  }

  public void setValidation(ValidationError validation) {
    this.validation = validation;
  }
}

package com.apps.pkador666.quality_api.model;

public class ValidationError {
  private boolean validation;
  private String message;

  public ValidationError() {
      this.validation = false;
      this.message = "";
  }

  public static ValidationError none() {
      return new ValidationError();
  }

  public static ValidationError of(String message) {
      ValidationError v = new ValidationError();
      v.validation = true;
      v.message = message;
      return v;
  }

  // Getters y Setters
  public boolean isValidation() { return validation; }
  public void setValidation(boolean validation) { this.validation = validation; }
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
}

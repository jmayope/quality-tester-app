package com.apps.pkador666.quality_api.model;

public class ErrorDetail {
  private boolean error;
    private String message;

    public ErrorDetail() {
        this.error = false;
        this.message = "";
    }

    public static ErrorDetail none() {
        return new ErrorDetail();
    }

    public static ErrorDetail of(String message) {
        ErrorDetail e = new ErrorDetail();
        e.error = true;
        e.message = message;
        return e;
    }

    // Getters y Setters
    public boolean isError() { return error; }
    public void setError(boolean error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

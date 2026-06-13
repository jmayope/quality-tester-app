package com.apps.pkador666.quality_api.dto.request;

import java.util.Optional;

public class TypeRequest {
  private Optional<Long> id;
  private String category;
  private String code;
  private String name;
  private Optional<String> additionalFields;
  private Optional<Boolean> status;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Optional<String> getAdditionalFields() {
    return additionalFields;
  }
  public void setAdditionalFields(Optional<String> additionalFields) {
    this.additionalFields = additionalFields;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  
}

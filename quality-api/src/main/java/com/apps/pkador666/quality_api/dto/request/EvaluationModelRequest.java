package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;
import java.util.Optional;

public class EvaluationModelRequest {
  private Optional<Long> id;
  private String code;
  private String abbr;
  private String name;
  private String description;
  private Optional<Boolean> status;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getAbbr() {
    return abbr;
  }
  public void setAbbr(String abbr) {
    this.abbr = abbr;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  
}

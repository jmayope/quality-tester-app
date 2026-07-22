package com.apps.pkador666.quality_api.dto.request;

import java.util.Optional;

public class ScaleRequest {
  private String name;
  private Optional<Double> value;
  private Optional<Double> from;
  private Optional<Double> until;
  private Long scaleTypeId;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Optional<Double> getValue() {
    return value;
  }
  public void setValue(Optional<Double> value) {
    this.value = value;
  }
  public Optional<Double> getFrom() {
    return from;
  }
  public void setFrom(Optional<Double> from) {
    this.from = from;
  }
  public Optional<Double> getUntil() {
    return until;
  }
  public void setUntil(Optional<Double> until) {
    this.until = until;
  }
  public Long getScaleTypeId() {
    return scaleTypeId;
  }
  public void setScaleTypeId(Long scaleTypeId) {
    this.scaleTypeId = scaleTypeId;
  }

  
  
}

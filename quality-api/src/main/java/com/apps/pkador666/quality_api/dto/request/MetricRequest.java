package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;
import java.util.Optional;

public class MetricRequest {
  private Optional<Long> id;
  private Long categoryId;
  private Long metricAttributeId;
  private String name;
  private Long meditionTypeId;
  private Optional<Double> minValue;
  private Optional<Double> maxValue;
  private Optional<Boolean> status;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public Long getCategoryId() {
    return categoryId;
  }
  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }
  public Long getMetricAttributeId() {
    return metricAttributeId;
  }
  public void setMetricAttributeId(Long metricAttributeId) {
    this.metricAttributeId = metricAttributeId;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Long getMeditionTypeId() {
    return meditionTypeId;
  }
  public void setMeditionTypeId(Long meditionTypeId) {
    this.meditionTypeId = meditionTypeId;
  }
  public Optional<Double> getMinValue() {
    return minValue;
  }
  public void setMinValue(Optional<Double> minValue) {
    this.minValue = minValue;
  }
  public Optional<Double> getMaxValue() {
    return maxValue;
  }
  public void setMaxValue(Optional<Double> maxValue) {
    this.maxValue = maxValue;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }
  
  
}

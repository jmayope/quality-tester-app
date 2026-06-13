package com.apps.pkador666.quality_api.dto.request;

import java.util.Optional;

public class MetricScaleRequest {
  private Optional<Long> id;
  private Long metricId;
  private Long scaleTypeId;
  private String name;
  private Optional<Double> value;
  private Optional<Double> from;
  private Optional<Double> until;
  private Optional<Boolean> status;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public Long getMetricId() {
    return metricId;
  }
  public void setMetricId(Long metricId) {
    this.metricId = metricId;
  }
  public Long getScaleTypeId() {
    return scaleTypeId;
  }
  public void setScaleTypeId(Long scaleTypeId) {
    this.scaleTypeId = scaleTypeId;
  }
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
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  
}

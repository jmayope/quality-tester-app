package com.apps.pkador666.quality_api.dto.request;

import java.util.Optional;

public class EvaluationMetricRequest {
  private Optional<Long> id;
  private Long evaluationModelId;
  private Long evaluationSectionId;
  private Long metricId;
  private Optional<Boolean> status;
  
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public Long getEvaluationModelId() {
    return evaluationModelId;
  }
  public void setEvaluationModelId(Long evaluationModelId) {
    this.evaluationModelId = evaluationModelId;
  }
  public Long getEvaluationSectionId() {
    return evaluationSectionId;
  }
  public void setEvaluationSectionId(Long evaluationSectionId) {
    this.evaluationSectionId = evaluationSectionId;
  }
  public Long getMetricId() {
    return metricId;
  }
  public void setMetricId(Long metricId) {
    this.metricId = metricId;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }
  
  
  
}

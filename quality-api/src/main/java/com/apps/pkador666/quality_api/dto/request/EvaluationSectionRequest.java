package com.apps.pkador666.quality_api.dto.request;

import java.util.Optional;

public class EvaluationSectionRequest {
  private Optional<Long> id;
  private Long evaluationModelId;
  private Long parent;
  private String name;
  private Long sectionOrder;
  private String description;
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
  public Long getParent() {
    return parent;
  }
  public void setParent(Long parent) {
    this.parent = parent;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Long getSectionOrder() {
    return sectionOrder;
  }
  public void setSectionOrder(Long sectionOrder) {
    this.sectionOrder = sectionOrder;
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

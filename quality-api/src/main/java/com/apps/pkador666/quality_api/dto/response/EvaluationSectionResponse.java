package com.apps.pkador666.quality_api.dto.response;

import java.util.List;
import java.util.Optional;

import com.apps.pkador666.quality_api.model.EvaluationSection;

public class EvaluationSectionResponse {
  
  private Optional<Long> id;
  private Long evaluationModelId;
  private Long parent;
  private String name;
  private Long sectionOrder;
  private String description;
  public List<EvaluationSectionResponse> sections;
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
  public List<EvaluationSectionResponse> getSections() {
    return sections;
  }
  public void setSections(List<EvaluationSectionResponse> sections) {
    this.sections = sections;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  
      
}

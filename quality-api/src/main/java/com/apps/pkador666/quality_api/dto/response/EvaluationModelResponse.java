package com.apps.pkador666.quality_api.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.apps.pkador666.quality_api.dto.request.EvaluationSectionRequest;

public class EvaluationModelResponse {
  
  private Optional<Long> id;
  private String code;
  private String abbr;
  private String name;
  private String description;
  private List<EvaluationSectionResponse> sections;
  private List<EvaluationMetricResponse> metrics;
  private Optional<Boolean> status;
  private Optional<LocalDateTime> createdAt;
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
  public List<EvaluationSectionResponse> getSections() {
    return sections;
  }
  public void setSections(List<EvaluationSectionResponse> sections) {
    this.sections = sections;
  }
  public List<EvaluationMetricResponse> getMetrics() {
    return metrics;
  }
  public void setMetrics(List<EvaluationMetricResponse> metrics) {
    this.metrics = metrics;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }
  public Optional<LocalDateTime> getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Optional<LocalDateTime> createdAt) {
    this.createdAt = createdAt;
  }
  
  
  
  
}

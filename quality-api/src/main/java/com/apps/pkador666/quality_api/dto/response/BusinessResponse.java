package com.apps.pkador666.quality_api.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.apps.pkador666.quality_api.model.EvaluableElement;

public class BusinessResponse {
  private Optional<Long> id;
  private String name;
  private String description;
  private Boolean status;
  private List<EvaluableElement> elements;
  private Optional<LocalDateTime> createAt;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
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
  public Boolean getStatus() {
    return status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
  }
  public List<EvaluableElement> getElements() {
    return elements;
  }
  public void setElements(List<EvaluableElement> elements) {
    this.elements = elements;
  }
  public Optional<LocalDateTime> getCreateAt() {
    return createAt;
  }
  public void setCreateAt(Optional<LocalDateTime> createAt) {
    this.createAt = createAt;
  }
  
}

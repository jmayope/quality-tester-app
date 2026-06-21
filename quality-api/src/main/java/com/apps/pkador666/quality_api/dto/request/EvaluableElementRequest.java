package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;

public class EvaluableElementRequest {
  private Optional<Long> id;

  private Optional<Long> businessId;

  private String code;

  private String name;

  private String description;

  private String technologies;

  private Optional<Boolean> status;

  private Optional<LocalDateTime> createAt;

  public Optional<Long> getId() {
    return id;
  }

  public void setId(Optional<Long> id) {
    this.id = id;
  }

  public Optional<Long> getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Optional<Long> businessId) {
    this.businessId = businessId;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTechnologies() {
    return technologies;
  }

  public void setTechnologies(String technologies) {
    this.technologies = technologies;
  }

  public Optional<Boolean> getStatus() {
    return status;
  }

  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  public Optional<LocalDateTime> getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Optional<LocalDateTime> createAt) {
    this.createAt = createAt;
  }

        
}

package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;
import java.util.Optional;

public class BusinessRequest {
  private Optional<Long> id;
  private String name;
  private String description;
  private Boolean status;
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
  public Optional<LocalDateTime> getCreateAt() {
    return createAt;
  }
  public void setCreateAt(Optional<LocalDateTime> createAt) {
    this.createAt = createAt;
  }
 
  
}

package com.apps.pkador666.quality_api.dto.request;

import java.util.Optional;

import jakarta.persistence.Column;

public class RoleRequest {
  private Long id;
  private String name;
  private Optional<Boolean> status;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  
}

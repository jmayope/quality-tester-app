package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;
import java.util.Optional;

public class BusinessUserRequest {
  private Optional<Long> id;
  private Long businessId;
  private Long userId;
  private Boolean status;
  private Optional<LocalDateTime> createAt;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public Long getBusinessId() {
    return businessId;
  }
  public void setBusinessId(Long businessId) {
    this.businessId = businessId;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
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

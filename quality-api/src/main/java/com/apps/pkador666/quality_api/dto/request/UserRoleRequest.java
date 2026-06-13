package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRoleRequest {
  private Optional<Long> id;
  private Long userId;
  private Long roleId;
  private Optional<Boolean> status;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public Long getRoleId() {
    return roleId;
  }
  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }
    
}

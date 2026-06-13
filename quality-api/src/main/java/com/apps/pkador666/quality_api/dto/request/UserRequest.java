package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRequest {
  private Optional<Long> id;
  private Long personId;
  private String username;
  private String password;  
  private String userType;
  private Boolean isAdmin;
  private LocalDateTime createAt;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public Long getPersonId() {
    return personId;
  }
  public void setPersonId(Long personId) {
    this.personId = personId;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getUserType() {
    return userType;
  }
  public void setUserType(String userType) {
    this.userType = userType;
  }
  public Boolean getIsAdmin() {
    return isAdmin;
  }
  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
  public LocalDateTime getCreateAt() {
    return createAt;
  }
  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }
    
}

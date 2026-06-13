package com.apps.pkador666.quality_api.dto.response;

import java.time.LocalDateTime;

import com.apps.pkador666.quality_api.model.Person;

public class UserResponse {
  private Long id;
  private Person person;
  private String username;
  private String password;
  private Boolean status;
  private String userType;
  private Boolean isAdmin;
  private LocalDateTime createAt;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Person getPerson() {
    return person;
  }
  public void setPerson(Person person) {
    this.person = person;
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
  public Boolean getStatus() {
    return status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
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

package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDate;
import java.util.Optional;

public class PersonRequest {
  private Optional<Long> id;
  private String fullName;
  private String email;
  private LocalDate birthDate;
  private Boolean gender;
  private Optional<Boolean> status;
  public Optional<Long> getId() {
    return id;
  }
  public void setId(Optional<Long> id) {
    this.id = id;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public LocalDate getBirthDate() {
    return birthDate;
  }
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
  public Boolean getGender() {
    return gender;
  }
  public void setGender(Boolean gender) {
    this.gender = gender;
  }
  public Optional<Boolean> getStatus() {
    return status;
  }
  public void setStatus(Optional<Boolean> status) {
    this.status = status;
  }

  
}

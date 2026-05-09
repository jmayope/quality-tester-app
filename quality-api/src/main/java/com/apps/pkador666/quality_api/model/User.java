package com.apps.pkador666.quality_api.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private int person_id;

  @Column()
  private String username;

  @Column()
  private String password;

  @Column()
  private Boolean status;

  @Column(name="user_type")
  private String userType;

  private Boolean is_admin;

  @CreationTimestamp
  @Column(name="create_at", nullable = false, updatable = false)
  private LocalDateTime createAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getPerson_id() {
    return person_id;
  }

  public void setPerson_id(int person_id) {
    this.person_id = person_id;
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

  public Boolean getIs_admin() {
    return is_admin;
  }

  public void setIs_admin(Boolean is_admin) {
    this.is_admin = is_admin;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  

}

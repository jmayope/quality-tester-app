package com.apps.pkador666.quality_api.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name="evaluable_elements")
public class EvaluableElement {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="business_id")
  private Long businessId;

  @Column()
  private String code;

  @Column()
  private String name;

  @Column()
  private String description;

  @Column()
  private String technologies;

  @Column()
  private Boolean status;

  @CreationTimestamp
  @Column(name="create_at", nullable = false, updatable = false)
  private LocalDateTime createAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBusinessId() {
    return businessId;
  }

  public void setBusinessId(Long businessId) {
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

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  
}

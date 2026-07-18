package com.apps.pkador666.quality_api.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluation_result_details")
public class EvaluationResultDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private Long evaluationResultId;

  @Column()
  private Long evaluationSectionId;

  @Column()
  private String score;

  @Column()
  private String obs;

  @Column()
  private Boolean status;

  @CreationTimestamp
  @Column(name="created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEvaluationResultId() {
    return evaluationResultId;
  }

  public void setEvaluationResultId(Long evaluationResultId) {
    this.evaluationResultId = evaluationResultId;
  }

  public Long getEvaluationSectionId() {
    return evaluationSectionId;
  }

  public void setEvaluationSectionId(Long evaluationSectionId) {
    this.evaluationSectionId = evaluationSectionId;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
  }

  public String getObs() {
    return obs;
  }

  public void setObs(String obs) {
    this.obs = obs;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  
  
}

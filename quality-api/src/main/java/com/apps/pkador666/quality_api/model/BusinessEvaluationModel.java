package com.apps.pkador666.quality_api.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name="business_evaluation_models")
public class BusinessEvaluationModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "business_id", nullable = false)
  private Business business;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "evaluation_model_id", nullable = false)
  private EvaluationModel evaluationModel;

  @Column()
  private Boolean status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_by_id", nullable = false)
  private BusinessUser creatorBy;

  @CreationTimestamp
  @Column(name="create_at", nullable = false, updatable = false)
  private LocalDateTime createAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
  }

  public EvaluationModel getEvaluationModel() {
    return evaluationModel;
  }

  public void setEvaluationModel(EvaluationModel evaluationModel) {
    this.evaluationModel = evaluationModel;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public BusinessUser getCreatorBy() {
    return creatorBy;
  }

  public void setCreatorBy(BusinessUser creatorBy) {
    this.creatorBy = creatorBy;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  
}

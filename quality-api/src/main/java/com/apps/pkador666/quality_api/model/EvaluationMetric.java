package com.apps.pkador666.quality_api.model;

import jakarta.persistence.*;

@Entity
@Table(name="evaluation_metrics")
public class EvaluationMetric {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "evaluation_model_id", nullable = false)
  private EvaluationModel evaluationModel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "evaluation_section_id", nullable = false)
  private EvaluationSection evaluationSection;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "metric_id", nullable = false)
  private Metric metric;

  @Column(nullable = false)
  private Boolean status = true;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EvaluationModel getEvaluationModel() {
    return evaluationModel;
  }

  public void setEvaluationModel(EvaluationModel evaluationModel) {
    this.evaluationModel = evaluationModel;
  }

  public EvaluationSection getEvaluationSection() {
    return evaluationSection;
  }

  public void setEvaluationSection(EvaluationSection evaluationSection) {
    this.evaluationSection = evaluationSection;
  }

  public Metric getMetric() {
    return metric;
  }

  public void setMetric(Metric metric) {
    this.metric = metric;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  
  
}

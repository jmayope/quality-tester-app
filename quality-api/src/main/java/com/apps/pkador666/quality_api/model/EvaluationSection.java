package com.apps.pkador666.quality_api.model;

import jakarta.persistence.*;

@Entity
@Table(name="evaluation_sections")
public class EvaluationSection {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "evaluation_model_id", nullable = false)
  private EvaluationModel evaluationModel;

  @Column()
  private Long parent;

  @Column()
  private String name;

  @Column(name="section_order")
  private Long sectionOrder;

  @Column()
  private String description;

  @Column()
  private Boolean status;

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

  public Long getParent() {
    return parent;
  }

  public void setParent(Long parent) {
    this.parent = parent;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getSectionOrder() {
    return sectionOrder;
  }

  public void setSectionOrder(Long sectionOrder) {
    this.sectionOrder = sectionOrder;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
  
  
}

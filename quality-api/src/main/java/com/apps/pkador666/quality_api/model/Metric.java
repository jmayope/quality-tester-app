package com.apps.pkador666.quality_api.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name="metrics")
public class Metric {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category", nullable = false)
  private Type category;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "metric_attribute", nullable = false)
  private Type metricAttribute;

  @Column()
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "medition_type", nullable = false)
  private Type meditionType;

  @Column(name="min_value")
  private Double minValue;

  @Column(name="max_value")
  private Double maxValue;

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

  public Type getCategory() {
    return category;
  }

  public void setCategory(Type category) {
    this.category = category;
  }

  public Type getMetricAttribute() {
    return metricAttribute;
  }

  public void setMetricAttribute(Type metricAttribute) {
    this.metricAttribute = metricAttribute;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Type getMeditionType() {
    return meditionType;
  }

  public void setMeditionType(Type meditionType) {
    this.meditionType = meditionType;
  }

  public Double getMinValue() {
    return minValue;
  }

  public void setMinValue(Double minValue) {
    this.minValue = minValue;
  }

  public Double getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(Double maxValue) {
    this.maxValue = maxValue;
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

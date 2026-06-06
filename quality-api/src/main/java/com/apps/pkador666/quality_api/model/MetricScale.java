package com.apps.pkador666.quality_api.model;

import jakarta.persistence.*;

@Entity
@Table(name="metric_scales")
public class MetricScale {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "metric_id", nullable = false)
  private Metric metric;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "scale_type", nullable = false)
  private Type scaleType;

  @Column()
  private String name;

  @Column()
  private Double value;

  @Column(name="from_value")
  private Double from;

  @Column()
  private Double until;

  @Column()
  private Boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Metric getMetric() {
    return metric;
  }

  public void setMetric(Metric metric) {
    this.metric = metric;
  }

  public Type getScaleType() {
    return scaleType;
  }

  public void setScaleType(Type scaleType) {
    this.scaleType = scaleType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public Double getFrom() {
    return from;
  }

  public void setFrom(Double from) {
    this.from = from;
  }

  public Double getUntil() {
    return until;
  }

  public void setUntil(Double until) {
    this.until = until;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
    
}

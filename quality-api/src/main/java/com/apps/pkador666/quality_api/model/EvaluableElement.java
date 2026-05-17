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

  @Column(name="entity_id")
  private Long entityId;

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
}

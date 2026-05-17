package com.apps.pkador666.quality_api.model;

import jakarta.persistence.*;

@Entity
@Table(name="evaluation_sections")
public class EvaluationSection {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private Long parent;

  @Column()
  private String name;

  @Column()
  private Long order;

  @Column()
  private String description;

  @Column()
  private Boolean status;

}

package com.apps.pkador666.quality_api.model;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;

  @Column()
  private Boolean status;
}

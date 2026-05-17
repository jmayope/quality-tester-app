package com.apps.pkador666.quality_api.model;

import org.springframework.boot.jackson.autoconfigure.JacksonProperties.Json;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name="types")
public class Type {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String category;

  @Column()
  private String code;

  @Column()
  private String name;

  @Column()
  private Json additionalFields;

  @Column()
  private String status;
}

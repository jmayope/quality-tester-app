package com.apps.pkador666.quality_api.model;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

  @JdbcTypeCode(SqlTypes.JSON)
  private String additionalFields;

  @Column()
  private String status;
}

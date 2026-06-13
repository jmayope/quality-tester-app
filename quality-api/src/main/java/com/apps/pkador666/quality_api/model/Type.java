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
  private Boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAdditionalFields() {
    return additionalFields;
  }

  public void setAdditionalFields(String additionalFields) {
    this.additionalFields = additionalFields;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  
}

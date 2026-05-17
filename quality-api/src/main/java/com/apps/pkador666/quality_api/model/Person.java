package com.apps.pkador666.quality_api.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="persons")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="full_name")
  private String fullName;

  @Column()
  private String email;

  @Column(name="birth_date")
  private LocalDate birthDate;
  
  @Column()
  private Boolean gender;

  @Column()
  private Boolean status;
}

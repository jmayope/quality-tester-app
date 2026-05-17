package com.apps.pkador666.quality_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.pkador666.quality_api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  
}

package com.apps.pkador666.quality_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.pkador666.quality_api.model.EvaluableElement;

public interface EvaluableElementRepository extends JpaRepository<EvaluableElement, Long>{
  
}

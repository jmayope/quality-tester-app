package com.apps.pkador666.quality_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.pkador666.quality_api.model.EvaluationMetric;

public interface EvaluationMetricRepository extends JpaRepository<EvaluationMetric, Long> {
  
}

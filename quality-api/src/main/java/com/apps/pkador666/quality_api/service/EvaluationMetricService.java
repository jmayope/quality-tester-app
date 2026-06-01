package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluationMetric;
import com.apps.pkador666.quality_api.repository.EvaluationMetricRepository;

@Service
public class EvaluationMetricService {
  private final EvaluationMetricRepository evaluationMetricRepository;

  public EvaluationMetricService(EvaluationMetricRepository evaluationMetricRepository) {
    this.evaluationMetricRepository = evaluationMetricRepository;
  }

  public List<EvaluationMetric> findAll() {
    return evaluationMetricRepository.findAll();
  }

  public List<EvaluationMetric> createMany(List<EvaluationMetric> evaluationMetrics) {
    return evaluationMetricRepository.saveAll(evaluationMetrics);
  } 
}

package com.apps.pkador666.quality_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.response.EvaluationMetricResponse;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
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

  public List<EvaluationMetricResponse> findByEvaluationModelId(Long evaluationModelId) {
    List<EvaluationMetric> founds = evaluationMetricRepository.findAll().stream().filter(e -> e.getEvaluationModel().getId() == evaluationModelId).toList();
    List<EvaluationMetricResponse> response = new ArrayList<EvaluationMetricResponse>();
    founds.stream().forEach(e -> {
      EvaluationMetricResponse metric = new EvaluationMetricResponse();
      metric.setId(Optional.of(e.getId()));
      metric.setEvaluationModelId(e.getEvaluationModel().getId());
      metric.setEvaluationSectionId(e.getEvaluationSection().getId());
      metric.setStatus(Optional.of(e.getStatus()));
      response.add(metric);
    });

    return response;
  }


  public List<EvaluationMetric> createMany(List<EvaluationMetric> evaluationMetrics) {
    return evaluationMetricRepository.saveAll(evaluationMetrics);
  } 
}

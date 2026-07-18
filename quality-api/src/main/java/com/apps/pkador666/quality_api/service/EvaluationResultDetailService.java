package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluationResultDetail;
import com.apps.pkador666.quality_api.repository.EvaluationResultDetailRepository;

@Service
public class EvaluationResultDetailService {
  private final EvaluationResultDetailRepository evaluationResultDetailRepository;

  public EvaluationResultDetailService(
    EvaluationResultDetailRepository evaluationResultDetailRepository
  ) {
    this.evaluationResultDetailRepository = evaluationResultDetailRepository;
  }

  public List<EvaluationResultDetail> findAll() {
    return evaluationResultDetailRepository.findAll();
  }

  public EvaluationResultDetail create(EvaluationResultDetail evaluationResult) {
    return evaluationResultDetailRepository.save(evaluationResult);
  }

  public List<EvaluationResultDetail> findByEvaluationResultId(Long id) {
    return evaluationResultDetailRepository.findAll().stream().filter(e -> e.getEvaluationResultId().equals(id)).toList();
  }
}

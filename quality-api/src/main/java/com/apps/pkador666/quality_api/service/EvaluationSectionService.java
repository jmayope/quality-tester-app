package com.apps.pkador666.quality_api.service;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluationSection;
import com.apps.pkador666.quality_api.repository.EvaluationSectionRepository;

@Service
public class EvaluationSectionService {
  private final EvaluationSectionRepository evaluationSectionRepository;

  public EvaluationSectionService(EvaluationSectionRepository evaluationSectionRepository) {
    this.evaluationSectionRepository = evaluationSectionRepository;
  }

  public EvaluationSection create(Long businessId, Long parent, String name, Long order, String description, Boolean status) {
    EvaluationSection newEvaluationSection = new EvaluationSection();
    newEvaluationSection.setBusinessId(businessId);
    newEvaluationSection.setParent(parent);
    newEvaluationSection.setName(name);
    newEvaluationSection.setOrder(order);
    newEvaluationSection.setDescription(description);
    newEvaluationSection.setStatus(status);
    return evaluationSectionRepository.save(newEvaluationSection);
  }
}

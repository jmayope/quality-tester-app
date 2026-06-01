package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluationSection;
import com.apps.pkador666.quality_api.repository.EvaluationSectionRepository;

@Service
public class EvaluationSectionService {
  private final EvaluationSectionRepository evaluationSectionRepository;

  public EvaluationSectionService(EvaluationSectionRepository evaluationSectionRepository) {
    this.evaluationSectionRepository = evaluationSectionRepository;
  }

  public List<EvaluationSection> findAll() {
    return evaluationSectionRepository.findAll();
  }

  public List<EvaluationSection> createMany(List<EvaluationSection> evaluationSections) {
    return evaluationSectionRepository.saveAll(evaluationSections);
  }

  public EvaluationSection create(EvaluationSection evaluationSection) {
    EvaluationSection newEvaluationSection = new EvaluationSection();
    newEvaluationSection.setEvaluationModel(evaluationSection.getEvaluationModel());
    newEvaluationSection.setParent(evaluationSection.getParent());
    newEvaluationSection.setName(evaluationSection.getName());
    newEvaluationSection.setSectionOrder(evaluationSection.getSectionOrder());
    newEvaluationSection.setDescription(evaluationSection.getDescription());
    newEvaluationSection.setStatus(evaluationSection.getStatus());
    return evaluationSectionRepository.save(newEvaluationSection);
  }
}

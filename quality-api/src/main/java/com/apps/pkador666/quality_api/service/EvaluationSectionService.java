package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationSection;
import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;
import com.apps.pkador666.quality_api.repository.EvaluationSectionRepository;

@Service
public class EvaluationSectionService {
  private final EvaluationSectionRepository evaluationSectionRepository;
  private final EvaluationModelRepository evaluationModelRepository;

  public EvaluationSectionService(EvaluationSectionRepository evaluationSectionRepository, EvaluationModelRepository evaluationModelRepository) {
    this.evaluationSectionRepository = evaluationSectionRepository;
    this.evaluationModelRepository = evaluationModelRepository;
  }

  public List<EvaluationSection> findAll() {
    return evaluationSectionRepository.findAll();
  }

  public EvaluationSection create(Long evaluationModelId, Long parent, String name, Long sectionOrder, String description, Boolean status) {
    EvaluationSection newEvaluationSection = new EvaluationSection();
    Optional<EvaluationModel> evaluationModelFound = evaluationModelRepository.findById(evaluationModelId);
    newEvaluationSection.setEvaluationModel(evaluationModelFound.get());
    newEvaluationSection.setParent(parent);
    newEvaluationSection.setName(name);
    newEvaluationSection.setSectionOrder(sectionOrder);
    newEvaluationSection.setDescription(description);
    newEvaluationSection.setStatus(status);
    return evaluationSectionRepository.save(newEvaluationSection);
  }
}

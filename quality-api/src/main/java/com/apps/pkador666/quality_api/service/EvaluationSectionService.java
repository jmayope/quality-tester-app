package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationSectionRequest;
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

  public List<EvaluationSection> createMany(List<EvaluationSection> evaluationSections) {
    return evaluationSectionRepository.saveAll(evaluationSections);
  }

  public EvaluationSection create(EvaluationSectionRequest evaluationSection) {
    EvaluationSection evaluationSectionCreated = new EvaluationSection();
    Optional<EvaluationModel> evaluationModel = evaluationModelRepository.findById(evaluationSection.getEvaluationModelId());
    
    evaluationSectionCreated.setEvaluationModel(evaluationModel.get());
    evaluationSectionCreated.setParent(evaluationSection.getParent());
    evaluationSectionCreated.setName(evaluationSection.getName());
    evaluationSectionCreated.setSectionOrder(evaluationSection.getSectionOrder());
    evaluationSectionCreated.setDescription(evaluationSection.getDescription());
    evaluationSectionCreated.setStatus(evaluationSection.getStatus().get() == null ? true : evaluationSection.getStatus().get());
    return evaluationSectionRepository.save(evaluationSectionCreated);
  }
}

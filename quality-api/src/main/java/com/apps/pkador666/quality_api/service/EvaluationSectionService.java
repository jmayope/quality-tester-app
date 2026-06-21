package com.apps.pkador666.quality_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationSectionRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
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

  public List<EvaluationSectionResponse> findByEvaluationModelId(Long evaluationModelId) {
    List<EvaluationSection> founds = evaluationSectionRepository.findAll().stream().filter(e -> e.getEvaluationModel().getId() == evaluationModelId).toList();
    List<EvaluationSectionResponse> response = new ArrayList<EvaluationSectionResponse>();
    founds.stream().forEach(e -> {
      EvaluationSectionResponse section = new EvaluationSectionResponse();
      section.setId(Optional.of(e.getId()));
      section.setName(e.getName());
      section.setDescription(e.getDescription());
      section.setParent(e.getParent());
      section.setSectionOrder(e.getSectionOrder());
      section.setStatus(Optional.of(e.getStatus()));
      response.add(section);
    });

    return response;

    // if (founds.get() != null) {
    //   EvaluationSection founded = founds.get();
    //   EvaluationSectionResponse evaluation = new EvaluationSectionResponse();
    //   evaluation.setEvaluationModelId(founded.getId());
    //   evaluation.setId(Optional.of(founded.getId()));
    //   evaluation.setName(founded.getName());
    //   evaluation.setDescription(founded.getDescription());
    //   evaluation.setParent(founded.getParent());
    //   evaluation.setSectionOrder(founded.getSectionOrder());
    //   evaluation.setStatus(Optional.of(founded.getStatus()));
    //   return Optional.of(evaluation);
    // } else {
    //   return null;
    // }
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

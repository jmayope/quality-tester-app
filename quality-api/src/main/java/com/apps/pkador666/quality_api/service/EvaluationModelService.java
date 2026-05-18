package com.apps.pkador666.quality_api.service;

import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluationModel;

@Service
public class EvaluationModelService {
  private final EvaluationModelRepository evaluationModelRepository;

  public EvaluationModelService(EvaluationModelRepository evaluationModelRepository) {
    this.evaluationModelRepository = evaluationModelRepository;
  }

  public List<EvaluationModel> findAll() {
    return evaluationModelRepository.findAll();
  }

  public EvaluationModel create(String code, String abbr, String name, String description, Boolean status) {
    EvaluationModel newEvaluationModel = new EvaluationModel();
    newEvaluationModel.setCode(code);
    newEvaluationModel.setAbbr(abbr);
    newEvaluationModel.setName(name);
    newEvaluationModel.setDescription(description);
    newEvaluationModel.setStatus(status);
    return evaluationModelRepository.save(newEvaluationModel);
  }

  public EvaluationModel update(Long id, String code, String abbr, String name, String description, Boolean status) {
    Optional<EvaluationModel> newEvaluationModel = evaluationModelRepository.findById(id);
    newEvaluationModel.get().setCode(code);
    newEvaluationModel.get().setAbbr(abbr);
    newEvaluationModel.get().setName(name);
    newEvaluationModel.get().setDescription(description);
    newEvaluationModel.get().setStatus(status);
    return evaluationModelRepository.save(newEvaluationModel.get());
  }

}

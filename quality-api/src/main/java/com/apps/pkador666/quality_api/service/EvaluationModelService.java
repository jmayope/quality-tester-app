package com.apps.pkador666.quality_api.service;

import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;
import com.apps.pkador666.quality_api.repository.EvaluationSectionRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluationMetric;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationSection;

@Service
public class EvaluationModelService {
  private final EvaluationModelRepository evaluationModelRepository;
  private final EvaluationSectionService evaluationSectionService;
  private final EvaluationMetricService evaluationMetricService;

  public EvaluationModelService(
    EvaluationModelRepository evaluationModelRepository,
    EvaluationSectionService evaluationSectionService,
    EvaluationMetricService evaluationMetricService
  ) {
    this.evaluationModelRepository = evaluationModelRepository;
    this.evaluationSectionService = evaluationSectionService;
    this.evaluationMetricService = evaluationMetricService;
  }

  public List<EvaluationModel> findAll() {
    return evaluationModelRepository.findAll();
  }

  public EvaluationModel create(EvaluationModel newEvaluationModel) {
    EvaluationModel evaluationModel = new EvaluationModel();
    evaluationModel.setCode(newEvaluationModel.getCode());
    evaluationModel.setAbbr(newEvaluationModel.getAbbr());
    evaluationModel.setName(newEvaluationModel.getName());
    evaluationModel.setDescription(newEvaluationModel.getDescription());
    evaluationModel.setStatus(newEvaluationModel.getStatus());
    EvaluationModel evaluationModelCreated = evaluationModelRepository.save(evaluationModel);

    List<EvaluationSection> sectionCreateds = evaluationSectionService.createMany(newEvaluationModel.getSections());
    evaluationModelCreated.setSections(sectionCreateds);

    List<EvaluationMetric> metricCreateds = evaluationMetricService.createMany(newEvaluationModel.getEvaluationMetrics());
    evaluationModelCreated.setEvaluationMetrics(metricCreateds);

    return evaluationModelCreated;
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

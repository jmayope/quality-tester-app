package com.apps.pkador666.quality_api.service;

import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationModelRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationMetricResponse;
import com.apps.pkador666.quality_api.dto.response.EvaluationModelResponse;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
import com.apps.pkador666.quality_api.model.EvaluationModel;

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

  public List<EvaluationModelResponse> findAll() {
    List<EvaluationModel> evaluations = evaluationModelRepository.findAll();
    List<EvaluationModelResponse> response = new ArrayList<EvaluationModelResponse>();
    evaluations.forEach(e -> {
      EvaluationModelResponse evaluation = new EvaluationModelResponse();
      evaluation.setId(Optional.of(e.getId()));
      evaluation.setAbbr(e.getAbbr());
      evaluation.setCode(e.getCode());
      evaluation.setName(e.getName());
      evaluation.setStatus(Optional.of(e.getStatus()));
      evaluation.setCreatedAt(Optional.of(e.getCreatedAt()));
      evaluation.setDescription(e.getDescription());
      List<EvaluationSectionResponse> sections = evaluationSectionService.findByEvaluationModelId(e.getId());
      evaluation.setSections(sections);
      List<EvaluationMetricResponse> metrics = evaluationMetricService.findByEvaluationModelId(e.getId());
      evaluation.setMetrics(metrics);
      response.add(evaluation);
    });

    return response;
  }

  public EvaluationModel create(EvaluationModelRequest evaluationModel) {

    EvaluationModel evaluationModelCreated = new EvaluationModel();

    evaluationModelCreated.setCode(evaluationModel.getCode());
    evaluationModelCreated.setAbbr(evaluationModel.getAbbr());
    evaluationModelCreated.setName(evaluationModel.getName());
    evaluationModelCreated.setDescription(evaluationModel.getDescription());
    evaluationModelCreated.setStatus(evaluationModel.getStatus().get() == null ? true : evaluationModel.getStatus().get());

    return evaluationModelRepository.save(evaluationModelCreated);
  }

  public EvaluationModel update(EvaluationModelRequest evaluationModel) {
    Optional<EvaluationModel> evaluationModelUpdated = evaluationModelRepository.findById(evaluationModel.getId().get());
    evaluationModelUpdated.get().setCode(evaluationModel.getCode());
    evaluationModelUpdated.get().setAbbr(evaluationModel.getAbbr());
    evaluationModelUpdated.get().setName(evaluationModel.getName());
    evaluationModelUpdated.get().setDescription(evaluationModel.getDescription());
    evaluationModelUpdated.get().setStatus(evaluationModel.getStatus().get() == null ? true : evaluationModel.getStatus().get());
    return evaluationModelRepository.save(evaluationModelUpdated.get());
  }

}

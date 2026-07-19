package com.apps.pkador666.quality_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationMetricRequest;
import com.apps.pkador666.quality_api.dto.request.EvaluationSectionRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationMetricResponse;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
import com.apps.pkador666.quality_api.model.EvaluationMetric;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationSection;
import com.apps.pkador666.quality_api.model.Metric;
import com.apps.pkador666.quality_api.repository.EvaluationMetricRepository;
import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;
import com.apps.pkador666.quality_api.repository.EvaluationSectionRepository;

import jakarta.transaction.Transactional;

@Service
public class EvaluationMetricService {
  private final EvaluationMetricRepository evaluationMetricRepository;
  private final EvaluationModelRepository evaluationModelRepository;
  private final MetricService metricService;
  private final EvaluationSectionRepository evaluationSectionRepository;

  public EvaluationMetricService(EvaluationMetricRepository evaluationMetricRepository, MetricService metricService, EvaluationSectionRepository evaluationSectionRepository, EvaluationModelRepository evaluationModelRepository) {
    this.evaluationMetricRepository = evaluationMetricRepository;
    this.metricService = metricService;
    this.evaluationSectionRepository = evaluationSectionRepository;
    this.evaluationModelRepository = evaluationModelRepository;
  }

  public List<EvaluationMetric> findAll() {
    return evaluationMetricRepository.findAll();
  }

  @Transactional
  public void saveMetrics(
          EvaluationModel evaluation,
          List<EvaluationMetricRequest> requests) {

      for (EvaluationMetricRequest dto : requests) {

          Optional<Metric> metric = metricService.findById(dto.getMetricId());
          Optional<EvaluationSection> section = evaluationSectionRepository.findById(dto.getEvaluationSectionId());
          EvaluationMetric evaluationmetric = buildEntity(
                  dto,
                  evaluation,
                  metric.get(),
                  section.get()
          );
          evaluationMetricRepository.save(evaluationmetric);
      }

  }

  /**
   * Construye toda la jerarquía en memoria.
   */
  private EvaluationMetric buildEntity(
          EvaluationMetricRequest dto,
          EvaluationModel evaluation,
          Metric metric,
          EvaluationSection section
        ) {

      EvaluationMetric entity = new EvaluationMetric();

      entity.setEvaluationModel(evaluation);
      entity.setMetric(metric);
      entity.setEvaluationSection(section);
      return entity;

  }

  private EvaluationSectionResponse toResponse(EvaluationSection entity) {

    EvaluationSectionResponse response = new EvaluationSectionResponse();

    response.setId(Optional.of(entity.getId()));
    response.setName(entity.getName());
    response.setEvaluationModelId(entity.getEvaluationModel().getId());
    response.setDescription(entity.getDescription());
    response.setParent(entity.getParent().getId());
    response.setSectionOrder(entity.getSectionOrder());
    response.setStatus(Optional.of(entity.getStatus() == null ? true : false));

    if (entity.getSections() != null) {

        List<EvaluationSectionResponse> children = entity.getSections()
                .stream()
                .map(this::toResponse)
                .toList();

        response.setSections(children);

    }

    return response;

}

  public List<EvaluationMetricResponse> findByEvaluationModelId(Long evaluationModelId) {
    List<EvaluationMetricResponse> response = new ArrayList<EvaluationMetricResponse>();
    List<EvaluationMetric> founds = evaluationMetricRepository.findAll().stream().filter(e -> e.getEvaluationModel().getId() == evaluationModelId).toList();
    founds.stream().forEach(e -> {
      EvaluationMetricResponse metric = new EvaluationMetricResponse();
      metric.setId(Optional.of(e.getId()));
      metric.setEvaluationModelId(e.getEvaluationModel().getId());
      metric.setEvaluationSectionId(e.getEvaluationSection().getId());
      metric.setMetricId(e.getMetric().getId());
      metric.setStatus(Optional.of(e.getStatus()));
      response.add(metric);
    });

    return response;
  }

  public List<EvaluationMetricResponse> findByEvaluationModelIdEntity(Long evaluationModelId) {
    List<EvaluationMetric> founds = evaluationMetricRepository.findAll().stream().filter(e -> e.getEvaluationModel().getId() == evaluationModelId).toList();
    List<EvaluationMetricResponse> response = new ArrayList<EvaluationMetricResponse>();
    founds.stream().forEach(e -> {
      EvaluationMetricResponse metric = new EvaluationMetricResponse();
      metric.setId(Optional.of(e.getId()));
      metric.setEvaluationModelId(e.getEvaluationModel().getId());
      metric.setEvaluationSectionId(e.getEvaluationSection().getId());
      metric.setMetricId(e.getMetric().getId());
      metric.setStatus(Optional.of(e.getStatus()));
      response.add(metric);
    });

    return response;
  }



  public List<EvaluationMetric> createMany(List<EvaluationMetricRequest> evaluationMetrics) {

    List<EvaluationMetric> newEvaluationMetrics = new ArrayList<>();
    evaluationMetrics.forEach(e -> {
      EvaluationMetric evaluationMetric = new EvaluationMetric();
      Optional<EvaluationModel> evaluation = evaluationModelRepository.findById(e.getEvaluationModelId());
      Optional<EvaluationSection> section = evaluationSectionRepository.findById(e.getEvaluationSectionId());
      Optional<Metric> metric = metricService.findById(e.getMetricId());
      evaluationMetric.setEvaluationModel(evaluation.get()); 
      evaluationMetric.setEvaluationSection(section.get());
      evaluationMetric.setMetric(metric.get());
      newEvaluationMetrics.add(evaluationMetric);
    });
    return evaluationMetricRepository.saveAll(newEvaluationMetrics);
  } 
}

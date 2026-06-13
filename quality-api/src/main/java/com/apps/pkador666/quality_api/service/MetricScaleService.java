package com.apps.pkador666.quality_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.MetricScaleRequest;
import com.apps.pkador666.quality_api.model.Metric;
import com.apps.pkador666.quality_api.model.MetricScale;
import com.apps.pkador666.quality_api.model.Type;
import com.apps.pkador666.quality_api.repository.MetricRepository;
import com.apps.pkador666.quality_api.repository.MetricScaleRepository;
import com.apps.pkador666.quality_api.repository.TypeRepository;

@Service
public class MetricScaleService {
  private final MetricScaleRepository metricScaleRepository;
  private final MetricRepository metricRepository;
  private final TypeRepository typeRepository;

  public MetricScaleService(MetricScaleRepository metricScaleRepository, MetricRepository metricRepository, TypeRepository typeRepository) {
    this.metricScaleRepository = metricScaleRepository;
    this.metricRepository = metricRepository;
    this.typeRepository = typeRepository;
  }

  public List<MetricScale> findAll() {
    return metricScaleRepository.findAll();
  }

  public MetricScale create(MetricScaleRequest metricScale) {
    MetricScale metricScaleCreated = new MetricScale();
    Optional<Metric> metricFound = metricRepository.findById(metricScale.getMetricId());
    Optional<Type> scaleTypeFound = typeRepository.findById(metricScale.getScaleTypeId());

    metricScaleCreated.setMetric(metricFound.get());
    metricScaleCreated.setScaleType(scaleTypeFound.get());
    metricScaleCreated.setName(metricScale.getName());
    metricScaleCreated.setValue(metricScale.getValue().get() == null ? 0 : metricScale.getValue().get());
    metricScaleCreated.setFrom(metricScale.getFrom().get() == null ? 0 : metricScale.getFrom().get());
    metricScaleCreated.setUntil(metricScale.getUntil().get() == null ? 0 : metricScale.getUntil().get());
    metricScaleCreated.setStatus(metricScale.getStatus().get() == null ? true : metricScale.getStatus().get());

    return metricScaleRepository.save(metricScaleCreated);
  }

  public List<MetricScale> createMany(List<MetricScaleRequest> metricScales) {

    List<MetricScale> newMetricScales = new ArrayList<MetricScale>();

    metricScales.stream().forEach(m -> {
      MetricScale metricScaleCreated = new MetricScale();
      Optional<Metric> metricFound = metricRepository.findById(m.getMetricId());
      Optional<Type> scaleTypeFound = typeRepository.findById(m.getScaleTypeId());

      metricScaleCreated.setMetric(metricFound.get());
      metricScaleCreated.setScaleType(scaleTypeFound.get());
      metricScaleCreated.setName(m.getName());
      metricScaleCreated.setValue(m.getValue().get() == null ? 0 : m.getValue().get());
      metricScaleCreated.setFrom(m.getFrom().get() == null ? 0 : m.getFrom().get());
      metricScaleCreated.setUntil(m.getUntil().get() == null ? 0 : m.getUntil().get());
      metricScaleCreated.setStatus(m.getStatus().get() == null ? true : m.getStatus().get());
      newMetricScales.add(metricScaleCreated);
    });

    return metricScaleRepository.saveAll(newMetricScales);
  }
}

package com.apps.pkador666.quality_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.MetricRequest;
import com.apps.pkador666.quality_api.model.Metric;
import com.apps.pkador666.quality_api.model.Type;
import com.apps.pkador666.quality_api.repository.MetricRepository;
import com.apps.pkador666.quality_api.repository.TypeRepository;

@Service
public class MetricService {
  
  private final MetricRepository metricRepository;
  private final TypeRepository typeRepository;

  public MetricService(MetricRepository metricRepository, TypeRepository typeRepository) {
    this.metricRepository = metricRepository;
    this.typeRepository = typeRepository;
  }

  public List<Metric> findAll() {
    return metricRepository.findAll();
  }

  public Metric create(MetricRequest metric) {
    Metric newMetric = new Metric();

    Optional<Type> categoryFound = typeRepository.findById(metric.getCategoryId());
    Optional<Type> metricAttributeFound = typeRepository.findById(metric.getMetricAttributeId());
    Optional<Type> meditionTypeFound = typeRepository.findById(metric.getMeditionTypeId());

    newMetric.setName(metric.getName());
    newMetric.setCategory(categoryFound.get());
    newMetric.setMetricAttribute(metricAttributeFound.get());
    newMetric.setMeditionType(meditionTypeFound.get());
    newMetric.setMinValue(metric.getMinValue().get() == null ? 0 : metric.getMinValue().get());
    newMetric.setMaxValue(metric.getMaxValue().get() == null ? 0 : metric.getMaxValue().get());
    newMetric.setStatus(metric.getStatus().get() == null ? true : metric.getStatus().get());
    return metricRepository.save(newMetric);
  }

  public List<Metric> createMany(List<MetricRequest> metrics) {

    List<Metric> newMetrics = new ArrayList<Metric>();

    metrics.stream().forEach(m -> {
      Metric newMetric = new Metric();
      Optional<Type> categoryFound = typeRepository.findById(m.getCategoryId());
      Optional<Type> metricAttributeFound = typeRepository.findById(m.getMetricAttributeId());
      Optional<Type> meditionTypeFound = typeRepository.findById(m.getMeditionTypeId());

      newMetric.setName(m.getName());
      newMetric.setCategory(categoryFound.get());
      newMetric.setMetricAttribute(metricAttributeFound.get());
      newMetric.setMeditionType(meditionTypeFound.get());
      newMetric.setMinValue(m.getMinValue().get() == null ? 0 : m.getMinValue().get());
      newMetric.setMaxValue(m.getMaxValue().get() == null ? 0 : m.getMaxValue().get());
      newMetric.setStatus(m.getStatus().get() == null ? true : m.getStatus().get());
      newMetrics.add(newMetric);
    });

    return metricRepository.saveAll(newMetrics);
  }

}

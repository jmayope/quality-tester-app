package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.MetricScale;
import com.apps.pkador666.quality_api.repository.MetricScaleRepository;

@Service
public class MetricScaleService {
  private final MetricScaleRepository metricScaleRepository;

  public MetricScaleService(MetricScaleRepository metricScaleRepository) {
    this.metricScaleRepository = metricScaleRepository;
  }

  public List<MetricScale> findAll() {
    return metricScaleRepository.findAll();
  }

  public MetricScale create(MetricScale newMetricScale) {
    return metricScaleRepository.save(newMetricScale);
  }

  public List<MetricScale> createMany(List<MetricScale> newMetricScales) {
    return metricScaleRepository.saveAll(newMetricScales);
  }
}

package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.Metric;
import com.apps.pkador666.quality_api.repository.MetricRepository;

@Service
public class MetricService {
  
  private final MetricRepository metricRepository;

  public MetricService(MetricRepository metricRepository) {
    this.metricRepository = metricRepository;
  }

  public List<Metric> findAll() {
    return metricRepository.findAll();
  }

  public Metric create(Metric metric) {
    return metricRepository.save(metric);
  }

  public List<Metric> createMany(List<Metric> metrics) {
    return metricRepository.saveAll(metrics);
  }

}

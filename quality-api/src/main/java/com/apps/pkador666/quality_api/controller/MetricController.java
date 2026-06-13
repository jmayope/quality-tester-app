package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.MetricRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.Metric;
import com.apps.pkador666.quality_api.service.MetricService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/metrics")
public class MetricController {
  private final MetricService metricService;

  public MetricController(MetricService metricService) {
    this.metricService = metricService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Metric>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(metricService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Metric>> create(@RequestBody MetricRequest newMetric) {
      return ResponseEntity.ok(ApiResponse.success(metricService.create(newMetric), "Creación Correcta"));
  }
  
}

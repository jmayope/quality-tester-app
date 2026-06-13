package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.MetricScaleRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.MetricScale;
import com.apps.pkador666.quality_api.service.MetricScaleService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/metric-scales")
public class MetricScaleController {
  private final MetricScaleService metricScaleService;

  public MetricScaleController(MetricScaleService metricScaleService) {
    this.metricScaleService = metricScaleService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<MetricScale>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(metricScaleService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<MetricScale>> create(@RequestBody MetricScaleRequest newMetricScale) {
      return ResponseEntity.ok(ApiResponse.success(metricScaleService.create(newMetricScale), "Creación de Escala de Métrica Correcta"));
  }
  
  @PostMapping("list")
  public ResponseEntity<ApiResponse<List<MetricScale>>> createMany(@RequestBody List<MetricScaleRequest> newMetricScales) {
      return ResponseEntity.ok(ApiResponse.success(metricScaleService.createMany(newMetricScales), "Creación de lista de Escala de Metricas correcta"));
  }
  
  
}

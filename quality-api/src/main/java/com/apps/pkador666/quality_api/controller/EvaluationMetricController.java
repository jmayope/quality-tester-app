package com.apps.pkador666.quality_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.EvaluationMetricRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationMetricResponse;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationMetric;
import com.apps.pkador666.quality_api.service.EvaluationMetricService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/evaluation-metrics")
public class EvaluationMetricController {
  private final EvaluationMetricService evaluationMetricService;

  public EvaluationMetricController(EvaluationMetricService evaluationMetricService) {
    this.evaluationMetricService = evaluationMetricService;
  }

  @PostMapping("list")
  public ResponseEntity<ApiResponse<List<EvaluationMetric>>> createMany(@RequestBody List<EvaluationMetricRequest> metrics) {
    return ResponseEntity.ok(ApiResponse.success(evaluationMetricService.createMany(metrics), "Creación Completa"));
  }

  @GetMapping("by-evaluation-model/{evaluationModelId}")
  public ResponseEntity<ApiResponse<List<EvaluationMetric>>> findByEvaluationModelId(@PathVariable("evaluationModelId") Long evaluationModelId) {
    return ResponseEntity.ok(ApiResponse.success(evaluationMetricService.findByEvaluationModelId(evaluationModelId), "Listado por Modelo de Evaluacion"));
  }
  
}

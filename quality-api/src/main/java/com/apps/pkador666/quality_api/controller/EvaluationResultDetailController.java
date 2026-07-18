package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.EvaluationModelRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationModelResponse;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationResult;
import com.apps.pkador666.quality_api.model.EvaluationResultDetail;
import com.apps.pkador666.quality_api.service.EvaluationResultDetailService;

@RestController
@RequestMapping("/evaluation-result-details")
public class EvaluationResultDetailController {
  private final EvaluationResultDetailService evaluationResultDetailService;

  public EvaluationResultDetailController(
    EvaluationResultDetailService evaluationResultDetailService
  ) {
    this.evaluationResultDetailService = evaluationResultDetailService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluationResultDetail>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(evaluationResultDetailService.findAll(), "Listado correct"));
  }

  @GetMapping("/by-evaluation-result/{evaluationResultId}")
  public ResponseEntity<ApiResponse<List<EvaluationResultDetail>>> findByEvaluationResult(@PathVariable("evaluationResultId") Long evaluationResultId) {
      return ResponseEntity.ok(ApiResponse.success(evaluationResultDetailService.findByEvaluationResultId(evaluationResultId), "Listado correct"));
  }
  
  @PostMapping
  public ResponseEntity<ApiResponse<EvaluationResultDetail>> save(@RequestBody EvaluationResultDetail details) {
    try {
      return ResponseEntity.ok(ApiResponse.success(evaluationResultDetailService.create(details), "Guardado correcto"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
    }
  }
}

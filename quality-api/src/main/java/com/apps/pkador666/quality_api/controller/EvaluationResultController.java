package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationResult;
import com.apps.pkador666.quality_api.service.EvaluationResultService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/evaluation-results")
public class EvaluationResultController {
  private final EvaluationResultService evaluationResultService;

  public EvaluationResultController(
    EvaluationResultService evaluationResultService
  ) {
    this.evaluationResultService = evaluationResultService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluationResult>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(evaluationResultService.findAll(), "Listado correct"));
  }

  

}

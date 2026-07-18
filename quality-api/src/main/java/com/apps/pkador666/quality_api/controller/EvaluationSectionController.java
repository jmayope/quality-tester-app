package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.EvaluationSectionRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationSection;
import com.apps.pkador666.quality_api.service.EvaluationModelService;
import com.apps.pkador666.quality_api.service.EvaluationSectionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/evaluation-sections")
public class EvaluationSectionController {
  private final EvaluationModelService evaluationModelService;
  private final EvaluationSectionService evaluationSectionService;

  public EvaluationSectionController(EvaluationSectionService evaluationSectionService, EvaluationModelService evaluationModelService) {
    this.evaluationSectionService = evaluationSectionService;
    this.evaluationModelService = evaluationModelService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluationSection>>> findAll() {
    return ResponseEntity.ok(ApiResponse.success(evaluationSectionService.findAll(), "Listado correcto"));
  }

  @GetMapping("/by-evaluation-model/{evaluationModelId}")
  public ResponseEntity<ApiResponse<List<EvaluationSectionResponse>>> findByEvaluationModelId(@PathVariable("evaluationModelId") Long evaluationModelId) {
    return ResponseEntity.ok(ApiResponse.success(evaluationSectionService.findByEvaluationModelId(evaluationModelId), "Listado correcto"));
  }
}

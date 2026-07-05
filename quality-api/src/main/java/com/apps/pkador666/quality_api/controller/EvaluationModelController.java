package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.EvaluationModelRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationModelResponse;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;
import com.apps.pkador666.quality_api.service.EvaluationMetricService;
import com.apps.pkador666.quality_api.service.EvaluationModelService;
import com.apps.pkador666.quality_api.service.EvaluationSectionService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/evaluation-models")
public class EvaluationModelController {
  
  private final EvaluationModelService evaluationModelService;
  private final EvaluationSectionService evaluationSectionService;
  private final EvaluationMetricService evaluationMetricService;

  public EvaluationModelController(EvaluationModelService evaluationModelService, EvaluationSectionService evaluationSectionService, EvaluationMetricService evaluationMetricService) {
    this.evaluationModelService = evaluationModelService;
    this.evaluationSectionService = evaluationSectionService;
    this.evaluationMetricService = evaluationMetricService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluationModelResponse>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(evaluationModelService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<EvaluationModelResponse>> save(@RequestBody EvaluationModelRequest evaluationModel) {
    try {
      EvaluationModel evaluationModelCreated = evaluationModelService.create(evaluationModel);
      if (evaluationModel.getSections() != null) {
        evaluationSectionService.saveSections(evaluationModelCreated, evaluationModel.getSections());
      }
      EvaluationModelResponse evaluation = new EvaluationModelResponse();

      return ResponseEntity.ok(ApiResponse.success(evaluation, "Listado Correcto"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
    }
  }

  @PutMapping("/update")
  public ResponseEntity<ApiResponse<EvaluationModel>> update(@RequestBody EvaluationModelRequest evaluationModel) {    
    return ResponseEntity.ok(ApiResponse.success(evaluationModelService.update(evaluationModel), "Actualización CorrectA"));
  }
  
}

package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.EvaluationModelRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;
import com.apps.pkador666.quality_api.service.EvaluationModelService;

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

  public EvaluationModelController(EvaluationModelService evaluationModelService) {
    this.evaluationModelService = evaluationModelService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluationModel>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(evaluationModelService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<EvaluationModel>> save(@RequestBody EvaluationModelRequest evaluationModel) {
      return ResponseEntity.ok(ApiResponse.success(evaluationModelService.create(evaluationModel), "Listado Correcto"));
  }

  @PutMapping("/update")
  public ResponseEntity<ApiResponse<EvaluationModel>> update(@RequestBody EvaluationModelRequest evaluationModel) {    
    return ResponseEntity.ok(ApiResponse.success(evaluationModelService.update(evaluationModel), "Actualización CorrectA"));
  }
  
}

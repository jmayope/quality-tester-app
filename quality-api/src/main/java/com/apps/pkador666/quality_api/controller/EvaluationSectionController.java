package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationSection;
import com.apps.pkador666.quality_api.service.EvaluationSectionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/evaluation-sections")
public class EvaluationSectionController {
  private final EvaluationSectionService evaluationSectionService;

  public EvaluationSectionController(EvaluationSectionService evaluationSectionService) {
    this.evaluationSectionService = evaluationSectionService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluationSection>>> findAll() {
    return ResponseEntity.ok(evaluationSectionService.findAll());
  }
  
}

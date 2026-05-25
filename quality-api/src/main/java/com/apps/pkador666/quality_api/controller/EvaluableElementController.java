package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.service.EvaluableElementService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping
public class EvaluableElementController {
  private final EvaluableElementService evaluableElementService;

  public EvaluableElementController(EvaluableElementService evaluableElementService) {
    this.evaluableElementService = evaluableElementService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluableElement>>> findAll() {
      return ResponseEntity.ok(evaluableElementService.findAll());
  }
  
}

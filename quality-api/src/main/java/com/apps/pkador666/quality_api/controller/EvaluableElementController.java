package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.EvaluableElementRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.service.EvaluableElementService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/evaluable-elements")
public class EvaluableElementController {
  private final EvaluableElementService evaluableElementService;

  public EvaluableElementController(EvaluableElementService evaluableElementService) {
    this.evaluableElementService = evaluableElementService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluableElement>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(evaluableElementService.findAll(), "Listado Correcto"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Optional<EvaluableElement>>> findById(@PathVariable("id") Long id) {
      return ResponseEntity.ok(ApiResponse.success(evaluableElementService.findById(id), "Item correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<EvaluableElement>> create(@RequestBody EvaluableElementRequest evaluableElement) {
    EvaluableElement evaluableElementCreated = evaluableElementService.create(evaluableElement);
    return ResponseEntity.ok(ApiResponse.success(evaluableElementCreated, "Creación correcta"));
  }

  @PostMapping("list")
  public ResponseEntity<ApiResponse<List<EvaluableElement>>> createMany(@RequestBody List<EvaluableElementRequest> evaluableElements) {
    List<EvaluableElement> evaluableElementCreateds = evaluableElementService.createMany(evaluableElements);
    return ResponseEntity.ok(ApiResponse.success(evaluableElementCreateds, "Creación correcta"));
  }
  
  
}

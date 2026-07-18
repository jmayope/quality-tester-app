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

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.MediaType;




@RestController
@RequestMapping("/evaluation-models")
public class EvaluationModelController {
  
  private final EvaluationModelService evaluationModelService;
  private final EvaluationSectionService evaluationSectionService;

  public EvaluationModelController(EvaluationModelService evaluationModelService, EvaluationSectionService evaluationSectionService) {
    this.evaluationModelService = evaluationModelService;
    this.evaluationSectionService = evaluationSectionService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<EvaluationModelResponse>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(evaluationModelService.findAll(), "Listado Correcto"));
  }

  @GetMapping("{id}")
  public ResponseEntity<ApiResponse<Optional<EvaluationModel>>> findById(@PathVariable("id") Long id) {
      return ResponseEntity.ok(ApiResponse.success(evaluationModelService.findById(id), "Listado Correcto"));
  }

  @GetMapping("/report")
  public ResponseEntity<InputStreamResource> descargarReporteUsuarios() {
        ByteArrayInputStream pdf = evaluationModelService.generateReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte_usuarios.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
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

package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.EvaluationResultRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.EvaluationResult;
import com.apps.pkador666.quality_api.model.EvaluationResultDetail;
import com.apps.pkador666.quality_api.service.EvaluationResultService;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



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
      return ResponseEntity.ok(ApiResponse.success(evaluationResultService.findAll(), "Listado correcto"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Optional<EvaluationResult>>> findById(@PathVariable("id") Long id) {
      return ResponseEntity.ok(ApiResponse.success(evaluationResultService.findById(id), "Item correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<EvaluationResult>> save(@RequestBody EvaluationResultRequest evaluationResult) {
    try {
      return ResponseEntity.ok(ApiResponse.success(evaluationResultService.create(evaluationResult), "Guardado correcto"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<ApiResponse<EvaluationResult>> update(@PathVariable("id") Long id, @RequestBody EvaluationResultRequest evaluationResult) {
    try {
      return ResponseEntity.ok(ApiResponse.success(evaluationResultService.update(id, evaluationResult), "Guardado correcto"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
    }
  }

  @GetMapping("report/{id}")
  public ResponseEntity<InputStreamResource> descargarReporteUsuarios(@PathVariable("id") Long id) {
        ByteArrayInputStream pdf = evaluationResultService.generateStatsReport(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte_usuarios.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
  
  

}

package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/evaluation-models")
public class EvaluationModelController {
  
  private final EvaluationModelRepository evaluationModelRepository;

  public EvaluationModelController(EvaluationModelRepository evaluationModelRepository) {
    this.evaluationModelRepository = evaluationModelRepository;
  }

  @GetMapping
  public List<EvaluationModel> findAll() {
      return evaluationModelRepository.findAll();
  }

  @PostMapping
  public EvaluationModel save(@RequestBody EvaluationModel newEvaluationModel) {
      return evaluationModelRepository.save(newEvaluationModel);
  }

  @PutMapping("/update")
  public EvaluationModel putMethodName(@RequestBody EvaluationModel newEvaluationModel) {    
    return evaluationModelRepository.save(newEvaluationModel);
  }


  // @PostMapping("/update/{id}")
  // public EvaluationModel update(@PathVariable Long id, @RequestBody EvaluationModel newEvaluationModel) {
  //   EvaluationModel existing = evaluationModelRepository.findById(id)
  //   .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

  //   existing.setName(newEvaluationModel.getName());
  //   existing.setCode(newEvaluationModel.getCode());
  //   existing.setStatus(newEvaluationModel.getStatus());

  //   // Guarda cambios
  //   return evaluationModelRepository.save(existing);
  // }
  
}

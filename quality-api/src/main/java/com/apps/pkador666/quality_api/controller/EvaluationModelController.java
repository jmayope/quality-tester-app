package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

  @PostMapping("update")
  public EvaluationModel update(@RequestParam Integer id, @RequestBody EvaluationModel newEvaluationModel) {
      return evaluationModelRepository.save(newEvaluationModel);
  }
  
}

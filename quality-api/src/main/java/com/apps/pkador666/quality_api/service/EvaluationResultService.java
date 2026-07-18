package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationResultRequest;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationResult;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.EvaluationResultRepository;

@Service
public class EvaluationResultService {
  
  private final EvaluationResultRepository evaluationResultRepository;
  private final EvaluationModelService evaluationModelService;
  private final UserService userService;

  public EvaluationResultService(
    EvaluationResultRepository evaluationResultRepository,
    EvaluationModelService evaluationModelService,
    UserService userService
  ) {
    this.evaluationResultRepository = evaluationResultRepository;
    this.evaluationModelService = evaluationModelService;
    this.userService = userService;
  }

  public List<EvaluationResult> findAll() {
    return evaluationResultRepository.findAll();
  }

  public EvaluationResult create(EvaluationResultRequest evaluationResult) {
    Optional<EvaluationModel> evaluationModel = evaluationModelService.findById(evaluationResult.getEvaluationModelId());
    Optional<User> user = userService.findById(evaluationResult.getUserId());
    EvaluationResult newEvaluationResult = new EvaluationResult();
    newEvaluationResult.setEvaluationModel(evaluationModel.get());
    newEvaluationResult.setUser(user.get());
    newEvaluationResult.setState(evaluationResult.getState());

    return evaluationResultRepository.save(newEvaluationResult);
  }
}

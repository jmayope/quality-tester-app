package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationResultRequest;
import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationResult;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.EvaluationResultRepository;

@Service
public class EvaluationResultService {
  
  private final EvaluationResultRepository evaluationResultRepository;
  private final EvaluationModelService evaluationModelService;
  private final UserService userService;
  private final EvaluableElementService evaluableElementService;

  public EvaluationResultService(
    EvaluationResultRepository evaluationResultRepository,
    EvaluationModelService evaluationModelService,
    UserService userService,
    EvaluableElementService evaluableElementService
  ) {
    this.evaluationResultRepository = evaluationResultRepository;
    this.evaluationModelService = evaluationModelService;
    this.userService = userService;
    this.evaluableElementService = evaluableElementService;
  }

  public List<EvaluationResult> findAll() {
    return evaluationResultRepository.findAll();
  }

  public Optional<EvaluationResult> findById(Long id) {
    return evaluationResultRepository.findAll().stream().filter(e -> e.getId().equals(id)).findFirst();
  }

  public EvaluationResult create(EvaluationResultRequest evaluationResult) {
    Optional<EvaluationModel> evaluationModel = evaluationModelService.findById(evaluationResult.getEvaluationModelId());
    Optional<User> user = userService.findById(evaluationResult.getUserId());
    Optional<EvaluableElement> evaluableElement = evaluableElementService.findById(evaluationResult.getEvaluableElementId());

    EvaluationResult newEvaluationResult = new EvaluationResult();
    newEvaluationResult.setEvaluationModel(evaluationModel.get());
    newEvaluationResult.setUser(user.get());
    newEvaluationResult.setEvaluableElement(evaluableElement.get());
    newEvaluationResult.setState(evaluationResult.getState());

    return evaluationResultRepository.save(newEvaluationResult);
  }
}

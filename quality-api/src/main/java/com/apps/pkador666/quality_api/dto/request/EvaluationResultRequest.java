package com.apps.pkador666.quality_api.dto.request;

import java.time.LocalDateTime;

import com.apps.pkador666.quality_api.model.EvaluationModel;

public class EvaluationResultRequest {
  private Long id;
  private Long evaluationModelId;
  private Long userId;
  private String state;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Long getEvaluationModelId() {
    return evaluationModelId;
  }
  public void setEvaluationModelId(Long evaluationModelId) {
    this.evaluationModelId = evaluationModelId;
  }
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  
  
}

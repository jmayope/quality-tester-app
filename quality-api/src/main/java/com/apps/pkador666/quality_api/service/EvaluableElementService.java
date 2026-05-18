package com.apps.pkador666.quality_api.service;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.repository.EvaluableElementRepository;

@Service
public class EvaluableElementService {
  private final EvaluableElementRepository evaluableElementRepository;
  
  public EvaluableElementService(EvaluableElementRepository evaluableElementRepository) {
    this.evaluableElementRepository = evaluableElementRepository;
  }

  public EvaluableElement create(Long entityId, String code, String name, String description, String technologies, Boolean status) {
    EvaluableElement newEvaluableElement = new EvaluableElement();
    newEvaluableElement.setEntityId(entityId);
    newEvaluableElement.setCode(code);
    newEvaluableElement.setName(name);
    newEvaluableElement.setDescription(description);
    newEvaluableElement.setTechnologies(technologies);
    newEvaluableElement.setStatus(status);
    return evaluableElementRepository.save(newEvaluableElement);
  }

  
}

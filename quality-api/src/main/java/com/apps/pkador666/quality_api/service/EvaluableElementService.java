package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.Business;
import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.repository.BusinessRepository;
import com.apps.pkador666.quality_api.repository.EvaluableElementRepository;

@Service
public class EvaluableElementService {
  private final EvaluableElementRepository evaluableElementRepository;
  private final BusinessRepository businessRepository;
  
  
  public EvaluableElementService(EvaluableElementRepository evaluableElementRepository, BusinessRepository businessRepository) {
    this.evaluableElementRepository = evaluableElementRepository;
    this.businessRepository = businessRepository;
  }

  public List<EvaluableElement> findAll() {
    return evaluableElementRepository.findAll();
  }

  public EvaluableElement create(Long businessId, String code, String name, String description, String technologies, Boolean status) {
    EvaluableElement newEvaluableElement = new EvaluableElement();
    Optional<Business> businessFound = businessRepository.findById(businessId);
    newEvaluableElement.setBusiness(businessFound.get());
    newEvaluableElement.setCode(code);
    newEvaluableElement.setName(name);
    newEvaluableElement.setDescription(description);
    newEvaluableElement.setTechnologies(technologies);
    newEvaluableElement.setStatus(status);
    return evaluableElementRepository.save(newEvaluableElement);
  }

  
}

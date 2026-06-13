package com.apps.pkador666.quality_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluableElementRequest;
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

  public EvaluableElement create(EvaluableElementRequest evaluableElement) {
    EvaluableElement newEvaluableElement = new EvaluableElement();
    Optional<Business> businessFound = businessRepository.findById(evaluableElement.getBusinessId());
    newEvaluableElement.setBusiness(businessFound.get());
    newEvaluableElement.setCode(evaluableElement.getCode());
    newEvaluableElement.setName(evaluableElement.getName());
    newEvaluableElement.setDescription(evaluableElement.getDescription());
    newEvaluableElement.setTechnologies(evaluableElement.getTechnologies());
    newEvaluableElement.setStatus(evaluableElement.getStatus() == null ? true : evaluableElement.getStatus().get());
    return evaluableElementRepository.save(newEvaluableElement);
  }

  public List<EvaluableElement> createMany(List<EvaluableElementRequest> evaluableElements) {

    List<EvaluableElement> elements = new ArrayList<EvaluableElement>();

    evaluableElements.stream().forEach(e -> {
      EvaluableElement newEvaluableElement = new EvaluableElement();
      Optional<Business> businessFound = businessRepository.findById(e.getBusinessId());
      newEvaluableElement.setBusiness(businessFound.get());
      newEvaluableElement.setCode(e.getCode());
      newEvaluableElement.setName(e.getName());
      newEvaluableElement.setDescription(e.getDescription());
      newEvaluableElement.setTechnologies(e.getTechnologies());
      newEvaluableElement.setStatus(e.getStatus() == null ? true : e.getStatus().get());
      elements.add(newEvaluableElement);
    });

    return evaluableElementRepository.saveAll(elements);
  }

  
}

package com.apps.pkador666.quality_api.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.BusinessRequest;
import com.apps.pkador666.quality_api.dto.response.BusinessResponse;
import com.apps.pkador666.quality_api.model.Business;
import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.repository.BusinessRepository;
import com.apps.pkador666.quality_api.repository.EvaluableElementRepository;

@Service
public class BusinessService {
  private final BusinessRepository businessRepository;
  private final EvaluableElementRepository evaluableElementRepository;

  public BusinessService(BusinessRepository businessRepository, EvaluableElementRepository evaluableElementRepository) {
    this.businessRepository = businessRepository;
    this.evaluableElementRepository = evaluableElementRepository;
  }

  public List<BusinessResponse> findAll() {
    List<Business> allBusiness = businessRepository.findAll();

    List<BusinessResponse> response = new ArrayList<BusinessResponse>();

    allBusiness.stream().forEach(b -> {
      BusinessResponse business = new BusinessResponse();
      business.setId(Optional.ofNullable(b.getId()));
      business.setName(b.getName());
      business.setDescription(b.getDescription());
      business.setStatus(b.getStatus());
      List<EvaluableElement> elements = evaluableElementRepository.findAll().stream().filter(e -> e.getBusiness().getId() == b.getId()).toList();
      business.setElements(elements);
      response.add(business);
    });
    return response;
  }

  public Business create(BusinessRequest business) {
    Business newBusiness = new Business();
    newBusiness.setName(business.getName());
    newBusiness.setDescription(business.getDescription());
    newBusiness.setStatus(business.getStatus());
    return businessRepository.save(newBusiness);
  }

  public Business updateOne(Long id, String name, String description, Boolean status) {
    Optional<Business> businessToUpdate = businessRepository.findById(id);
    businessToUpdate.get().setName(name);
    businessToUpdate.get().setDescription(description);
    businessToUpdate.get().setStatus(status);
    return businessRepository.save(businessToUpdate.get());
  }

  public Boolean deleteOne(Long id) {
    businessRepository.deleteById(id);
    return true;
  }
}

package com.apps.pkador666.quality_api.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.Business;
import com.apps.pkador666.quality_api.repository.BusinessRepository;

@Service
public class BusinessService {
  private final BusinessRepository businessRepository;

  public BusinessService(BusinessRepository businessRepository) {
    this.businessRepository = businessRepository;
  }

  public Business create(String name, String description, Boolean status) {
    Business newBusiness = new Business();
    newBusiness.setName(name);
    newBusiness.setDescription(description);
    newBusiness.setStatus(status);
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

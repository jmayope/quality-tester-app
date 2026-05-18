package com.apps.pkador666.quality_api.service;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.BusinessUser;
import com.apps.pkador666.quality_api.repository.BusinessUserRepository;

@Service
public class BusinessUserService {
  private final BusinessUserRepository businessUserRepository;

  public BusinessUserService(BusinessUserRepository businessUserRepository) {
    this.businessUserRepository = businessUserRepository;
  }

  public BusinessUser create(Long businessId, Long userId) {
    BusinessUser newBusinessUser = new BusinessUser();
    newBusinessUser.setBusinessId(businessId);
    newBusinessUser.setUserId(userId);
    return businessUserRepository.save(newBusinessUser);
  }

  public Boolean deleteOne(Long id) {
    businessUserRepository.deleteById(id);
    return true;
  }
}

package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.Business;
import com.apps.pkador666.quality_api.model.BusinessUser;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.BusinessRepository;
import com.apps.pkador666.quality_api.repository.BusinessUserRepository;
import com.apps.pkador666.quality_api.repository.UserRepository;

@Service
public class BusinessUserService {
  private final BusinessUserRepository businessUserRepository;
  private final BusinessRepository businessRepository;
  private final UserRepository userRepository;
  
  public BusinessUserService(BusinessUserRepository businessUserRepository, BusinessRepository businessRepository, UserRepository userRepository) {
    this.businessUserRepository = businessUserRepository;
    this.businessRepository = businessRepository;
    this.userRepository = userRepository;
  }
  
  public List<BusinessUser> findAll() {
    return businessUserRepository.findAll();
  }

  public BusinessUser create(Long businessId, Long userId) {
    BusinessUser newBusinessUser = new BusinessUser();
    Optional<Business> businessFound = businessRepository.findById(businessId);
    newBusinessUser.setBusiness(businessFound.get());;
    Optional<User> userFound = userRepository.findById(userId);
    newBusinessUser.setUser(userFound.get());
    return businessUserRepository.save(newBusinessUser);
  }

  public Boolean deleteOne(Long id) {
    businessUserRepository.deleteById(id);
    return true;
  }
}

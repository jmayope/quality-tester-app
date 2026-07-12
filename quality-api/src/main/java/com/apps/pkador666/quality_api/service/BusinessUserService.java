package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.BusinessUserRequest;
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

  public BusinessUser create(BusinessUserRequest newBusinessUser) {
    BusinessUser businessUserCreated = new BusinessUser();
    Optional<Business> businessFound = businessRepository.findById(newBusinessUser.getBusinessId());
    businessUserCreated.setBusiness(businessFound.get());;
    Optional<User> userFound = userRepository.findById(newBusinessUser.getUserId());
    businessUserCreated.setUser(userFound.get());
    businessUserCreated.setStatus(newBusinessUser.getStatus());
    return businessUserRepository.save(businessUserCreated);
  }

  public Boolean deleteOne(Long id) {
    businessUserRepository.deleteById(id);
    return true;
  }
}

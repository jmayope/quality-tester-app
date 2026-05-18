package com.apps.pkador666.quality_api.service;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.UserRole;
import com.apps.pkador666.quality_api.repository.UserRoleRepository;

@Service
public class UserRoleService {
  private final UserRoleRepository userRoleRepository;

  public UserRoleService(UserRoleRepository userRoleRepository) {
    this.userRoleRepository = userRoleRepository;
  }

  public UserRole create(Long userId, long roleId, Boolean status) {
    UserRole newUserRole = new UserRole();
    newUserRole.setUserId(userId);
    newUserRole.setRoleId(userId);
    newUserRole.setStatus(status);
    return userRoleRepository.save(newUserRole);
  }
}

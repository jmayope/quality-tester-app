package com.apps.pkador666.quality_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.UserRoleRequest;
import com.apps.pkador666.quality_api.model.Role;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.model.UserRole;
import com.apps.pkador666.quality_api.repository.RoleRepository;
import com.apps.pkador666.quality_api.repository.UserRepository;
import com.apps.pkador666.quality_api.repository.UserRoleRepository;

@Service
public class UserRoleService {
  private final UserRoleRepository userRoleRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserRoleService(UserRoleRepository userRoleRepository, UserRepository userRepository, RoleRepository roleRepository) {
    this.userRoleRepository = userRoleRepository;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  public List<UserRole> findAll() {
    return userRoleRepository.findAll();
  }

  public UserRole create(UserRoleRequest userRole) {
    UserRole newUserRole = new UserRole();
    Optional<User> userFound = userRepository.findById(userRole.getUserId());
    newUserRole.setUser(userFound.get());
    Optional<Role> roleFound = roleRepository.findById(userRole.getRoleId());
    newUserRole.setRole(roleFound.get());
    newUserRole.setStatus(userRole.getStatus().get() == null ? true : userRole.getStatus().get());
    return userRoleRepository.save(newUserRole);
  }
}

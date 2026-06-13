package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.RoleRequest;
import com.apps.pkador666.quality_api.model.Role;
import com.apps.pkador666.quality_api.repository.RoleRepository;

@Service
public class RoleService {
  private final RoleRepository roleRepository;

  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public Role create(RoleRequest role) {
    Role newRole = new Role();
    newRole.setName(role.getName());
    newRole.setStatus(role.getStatus().get() == null ? true : role.getStatus().get());
    return roleRepository.save(newRole);
  }
}

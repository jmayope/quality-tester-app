package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

  public Role create(String name, Boolean status) {
    Role newRole = new Role();
    newRole.setName(name);
    newRole.setStatus(status);
    return roleRepository.save(newRole);
  }
}

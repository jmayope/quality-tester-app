package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.RoleRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.Role;
import com.apps.pkador666.quality_api.service.RoleService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/roles")
public class RoleController {
  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Role>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(roleService.findAll(), "Listado Correcto"));
  }
  
  @PostMapping
  public ResponseEntity<ApiResponse<Role>> postMethodName(@RequestBody RoleRequest role) {
      Role roleCreated = roleService.create(role);
      return ResponseEntity.ok(ApiResponse.success(roleCreated, "Listado Correcto"));
  }
  
}

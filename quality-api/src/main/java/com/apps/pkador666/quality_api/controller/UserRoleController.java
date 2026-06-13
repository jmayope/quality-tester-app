package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.UserRoleRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.UserRole;
import com.apps.pkador666.quality_api.service.UserRoleService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user-roles")
public class UserRoleController {
  private final UserRoleService userRoleService;

  public UserRoleController(UserRoleService userRoleService) {
    this.userRoleService = userRoleService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserRole>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(userRoleService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<UserRole>> create(@RequestBody UserRoleRequest userRole) {
      UserRole userRoleCreated = userRoleService.create(userRole);
      return ResponseEntity.ok(ApiResponse.success(userRoleCreated, "Listado Correcto"));
  }
  
  
}

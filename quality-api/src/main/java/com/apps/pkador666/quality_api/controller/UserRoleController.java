package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.UserRole;
import com.apps.pkador666.quality_api.service.UserRoleService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user-roles")
public class UserRoleController {
  private final UserRoleService userRoleService;

  public UserRoleController(UserRoleService userRoleService) {
    this.userRoleService = userRoleService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserRole>>> findAll() {
      return ResponseEntity.ok(userRoleService.findAll());
  }
  
}

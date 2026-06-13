package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.BusinessUserRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.BusinessUser;
import com.apps.pkador666.quality_api.service.BusinessUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/business-users")
public class BusinessUserController {
  private final BusinessUserService businessUserService;

  public BusinessUserController(BusinessUserService _businessUserService) {
    this.businessUserService = _businessUserService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<BusinessUser>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(businessUserService.findAll(), "Listado correcto"));
  }
  
  @PostMapping
  public ResponseEntity<ApiResponse<BusinessUser>> create(@RequestBody BusinessUserRequest newBusinessUser) {
    BusinessUser businessUserCreated = businessUserService.create(newBusinessUser);
    return ResponseEntity.ok(ApiResponse.success(businessUserCreated, "Registro correcto."));
  }
  
  
}

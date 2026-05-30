package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.BusinessUser;
import com.apps.pkador666.quality_api.service.BusinessUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
  
  
}

package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.BusinessRequest;
import com.apps.pkador666.quality_api.dto.response.BusinessResponse;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.Business;
import com.apps.pkador666.quality_api.service.BusinessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/business")
public class BusinessController {
  
  private final BusinessService businessService;

  public BusinessController(BusinessService businessService) {
    this.businessService = businessService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<BusinessResponse>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(businessService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Business>> register(@RequestBody BusinessRequest newBusiness) {
      try {
        Business businessCreated = businessService.create(newBusiness);
        return ResponseEntity.ok(ApiResponse.success(businessCreated, "Registro correcto."));
      } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
      }
  }

}

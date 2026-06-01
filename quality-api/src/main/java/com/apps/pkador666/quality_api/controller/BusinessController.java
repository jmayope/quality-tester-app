package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.Business;
import com.apps.pkador666.quality_api.service.BusinessService;

@RestController
@RequestMapping("/business")
public class BusinessController {
  
  private final BusinessService businessService;

  public BusinessController(BusinessService businessService) {
    this.businessService = businessService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Business>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(businessService.findAll(), "Listado Correcto"));
  }

  // @PostMapping
  // public ResponseEntity<ApiResponse<Business>> register(@RequestBody Business newUser) {
  //     try {
  //       User businessCreated = bu.register(newUser.getPersonId(), newUser.getUsername(), newUser.getPassword(), newUser.getIsAdmin());
  //       return ResponseEntity.ok(ApiResponse.success(businessCreated, "Registro correcto."));
  //     } catch (RuntimeException e) {
  //       return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
  //     }
  // }

}

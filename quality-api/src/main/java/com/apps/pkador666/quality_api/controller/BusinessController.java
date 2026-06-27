package com.apps.pkador666.quality_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.BusinessRequest;
import com.apps.pkador666.quality_api.dto.response.BusinessResponse;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.Business;
import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.service.BusinessService;
import com.apps.pkador666.quality_api.service.EvaluableElementService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/business")
public class BusinessController {
  private final BusinessService businessService;
  private final EvaluableElementService evaluableElementService;

  public BusinessController(BusinessService businessService, EvaluableElementService evaluableElementService) {
    this.businessService = businessService;
    this.evaluableElementService = evaluableElementService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<BusinessResponse>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(businessService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<BusinessResponse>> register(@RequestBody BusinessRequest newBusiness) {
      try {
        Business businessCreated = businessService.create(newBusiness);

        newBusiness.getElements().stream().forEach(e -> {
          e.setBusinessId(Optional.of(businessCreated.getId()));
        });
        List<EvaluableElement> elements = evaluableElementService.createMany(newBusiness.getElements());
        BusinessResponse business = new BusinessResponse();
        business.setId(Optional.of(businessCreated.getId()));
        business.setName(businessCreated.getName());
        business.setStatus(businessCreated.getStatus());
        business.setDescription(businessCreated.getDescription());
        business.setElements(elements);
        business.setCreateAt(newBusiness.getCreateAt());
        return ResponseEntity.ok(ApiResponse.success(business, "Registro correcto."));
      } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
      }
  }

  @PutMapping
  public ResponseEntity<ApiResponse<BusinessResponse>> update(@RequestBody BusinessRequest businessToUpdate) {
      try {
        List<EvaluableElement> elements = evaluableElementService.updateMany(businessToUpdate.getElements());
        Business businessUpdated =  businessService.updateOne(businessToUpdate.getId().get(), businessToUpdate.getName(), businessToUpdate.getDescription(), businessToUpdate.getStatus().get());
        BusinessResponse business = new BusinessResponse();
        business.setId(Optional.of(businessUpdated.getId()));
        business.setName(businessUpdated.getName());
        business.setStatus(businessUpdated.getStatus());
        business.setDescription(businessUpdated.getDescription());
        business.setElements(elements);
        business.setCreateAt(Optional.of(businessUpdated.getCreateAt()));
        return ResponseEntity.ok(ApiResponse.success(business, "Registro correcto."));
      } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro " + e.getMessage(), e.getMessage()));
      }
  }

}

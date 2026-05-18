package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.Type;
import com.apps.pkador666.quality_api.service.TypeService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/types")
public class TypeController {
  private final TypeService typeService;
  
  public TypeController(TypeService typeService) {
    this.typeService = typeService;
  }

  @GetMapping("/by-category/{category}")
  public List<Type> findAll(@RequestParam String category) {
      return typeService.findAll();
  }
  
}

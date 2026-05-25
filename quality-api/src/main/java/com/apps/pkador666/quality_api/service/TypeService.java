package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.Type;
import com.apps.pkador666.quality_api.repository.TypeRepository;

@Service
public class TypeService {

  private final TypeService typeService;

  public TypeService(TypeService typeService) {
    this.typeService = typeService;
  }

  public List<Type> findAll() {
    return typeService.findAll();
  }

  public Type create(Type newType) {
    Type type = new Type();
    type.setCategory(newType.getCategory());
    type.setCode(newType.getCode());
    type.setName(newType.getName());
    type.setAdditionalFields(newType.getAdditionalFields());
    return typeService.save(type);
  }

}
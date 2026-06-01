package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.Type;
import com.apps.pkador666.quality_api.repository.TypeRepository;

@Service
public class TypeService {

  private final TypeRepository typeRepository;

  public TypeService(TypeRepository typeRepository) {
    this.typeRepository = typeRepository;
  }

  public List<Type> findAll() {
    return typeRepository.findAll();
  }

  public Type create(Type type) {
    Type newType = new Type();
    newType.setCategory(type.getCategory());
    newType.setCode(type.getCode());
    newType.setName(type.getName());
    newType.setStatus(type.getStatus());
    newType.setAdditionalFields(type.getAdditionalFields());
    return typeRepository.save(newType);
  }

}
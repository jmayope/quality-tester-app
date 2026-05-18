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

  public Type create(String category, String code, String name, String additionalFields) {
    Type newType = new Type();
    newType.setCategory(category);
    newType.setCode(code);
    newType.setName(name);
    newType.setAdditionalFields(additionalFields);
    return typeRepository.save(newType);
  }

}
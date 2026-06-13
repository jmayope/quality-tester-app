package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.PersonRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.Person;
import com.apps.pkador666.quality_api.service.PersonService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/people")
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Person>>> findAll() {
      return ResponseEntity.ok(ApiResponse.success(personService.findAll(), "Listado Correcto"));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Person>> postMethodName(@RequestBody PersonRequest person) {
      Person personCreated = personService.create(person);
      return ResponseEntity.ok(ApiResponse.success(personCreated, "Registro Correcto"));
  }
  
  
}

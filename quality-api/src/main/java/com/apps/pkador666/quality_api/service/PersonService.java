package com.apps.pkador666.quality_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.Person;
import com.apps.pkador666.quality_api.repository.PersonRepository;

@Service
public class PersonService {
  private final PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Person> findAll() {
    return personRepository.findAll();
  }

  public Person create(String fullName, String email, LocalDate birthDate, Boolean gender, Boolean status) {
    Person newPerson = new Person();
    newPerson.setFullName(fullName);
    newPerson.setEmail(email);
    newPerson.setBirthDate(birthDate);
    newPerson.setGender(gender);
    newPerson.setStatus(status);
    return personRepository.save(newPerson);
  }
}

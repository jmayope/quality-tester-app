package com.apps.pkador666.quality_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.PersonRequest;
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

  public List<Person> findByText(String text) {
    String textToSearch = text.toLowerCase();
    return personRepository.findAll().stream().filter( p-> p.getFullName().toLowerCase().contains(textToSearch) || p.getEmail().toLowerCase().contains(textToSearch)).toList();
  }

  public Person create(PersonRequest person) {
    Person newPerson = new Person();

    newPerson.setFullName(person.getFullName());
    newPerson.setEmail(person.getEmail());
    newPerson.setBirthDate(person.getBirthDate());
    newPerson.setGender(person.getGender());
    newPerson.setStatus(person.getStatus() == null ? true : person.getStatus().get());
    
    return personRepository.save(newPerson);
  }
}

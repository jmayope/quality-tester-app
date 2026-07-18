package com.apps.pkador666.quality_api.service;

import com.apps.pkador666.quality_api.repository.PersonRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.UserRequest;
import com.apps.pkador666.quality_api.model.Person;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.AuthRepository;
import com.apps.pkador666.quality_api.repository.UserRepository;

@Service
public class UserService {
  private final PersonRepository personRepository;
  private final AuthRepository authRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(AuthRepository authRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, PersonRepository personRepository) {
    this.authRepository = authRepository;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.personRepository = personRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  public User authenticate(String username, String password) {
    User user = authRepository.findByUsername(username)
      .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Contraseña Incorrecta");
    }

    return user;
  }

  public User register(UserRequest newUser) {
    User user = new User();
    user.setStatus(true);

    Optional<Person> personFound = personRepository.findById(newUser.getPersonId());

    user.setPerson(personFound.get());
    user.setUsername(newUser.getUsername());
    user.setPassword(passwordEncoder.encode(newUser.getPassword()));
    user.setIsAdmin(newUser.getIsAdmin());
    user.setUserType(newUser.getUserType());
    return userRepository.save(user);
  }

}

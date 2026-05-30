package com.apps.pkador666.quality_api.service;

import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.AuthRepository;
import com.apps.pkador666.quality_api.repository.UserRepository;

@Service
public class UserService {
  private final AuthRepository authRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(AuthRepository authRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.authRepository = authRepository;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User authenticate(String username, String password) {
    User user = authRepository.findByUsername(username)
      .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Contraseña Incorrecta");
    }

    return user;
  }

  public User register(User newUser) {
    User user = new User();
    user.setStatus(true);

    user.setPerson(newUser.getPerson());
    user.setUsername(newUser.getUsername());
    user.setPassword(passwordEncoder.encode(newUser.getPassword()));
    user.setIsAdmin(newUser.getIsAdmin());
    user.setUserType(newUser.getUserType());
    return userRepository.save(user);
  }

}

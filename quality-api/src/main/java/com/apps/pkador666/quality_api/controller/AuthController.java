package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.AuthRepository;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
  private final AuthRepository authRepository;

  public AuthController(AuthRepository authRepository) {
    this.authRepository = authRepository;
  }

  @PostMapping("/login")
  public User login(@RequestBody User user) {
    
    List<User> users = authRepository.findAll(
        (root, query, cb) -> cb.and(
          cb.equal(root.get("username"), user.getUsername()),
          cb.equal(root.get("password"), user.getPassword())
        )
      );
    if (users.size() > 0) {
      return users.getFirst();
    } else {
      return null;
    }
  }
  

  
}

package com.apps.pkador666.quality_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.UserRepository;
import com.apps.pkador666.quality_api.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<User>>> findAll() {
      return ResponseEntity.ok(userService.findAll());
  }

  @PostMapping
  public ResponseEntity<ApiResponse<User>> create(@RequestBody User newUser) {
      return ResponseEntity.ok(userService.register(newUser));
  }
  
}

package com.apps.pkador666.quality_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.pkador666.quality_api.dto.request.UserRequest;
import com.apps.pkador666.quality_api.model.ApiResponse;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("register")
  public ResponseEntity<ApiResponse<User>> register(@RequestBody UserRequest newUser) {
      try {
        User userCreated = userService.register(newUser);
        return ResponseEntity.ok(ApiResponse.success(userCreated, "Registro correcto."));
      } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(ApiResponse.validation("Error al momento de registro", e.getMessage()));
      }
  }
  

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<User>> login(@RequestBody User user) {
    try {
      User userAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());
      return ResponseEntity.ok(ApiResponse.success(userAuthenticated, "Usuario autenticado exitosamente."));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(ApiResponse.validation("Credenciales incorrectas.", e.getMessage()));
    }
  }
  
}

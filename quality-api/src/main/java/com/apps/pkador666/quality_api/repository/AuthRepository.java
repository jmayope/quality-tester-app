package com.apps.pkador666.quality_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.apps.pkador666.quality_api.model.User;

public interface AuthRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
  
}

package com.ead.authuser.services;

import com.ead.authuser.models.UserModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {

  List<UserModel> findAll();

  Optional<UserModel> findById(UUID userId);


  UserModel save(UserModel userModel);

  boolean existsByusername(String userName);

  boolean existsByEmail(String email);

  Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

  UserModel saveUser(UserModel userModel);
  void deleteUser(UserModel userModel);
  UserModel updateUser(UserModel userModel);
  UserModel updatePassword(UserModel userModel);
}

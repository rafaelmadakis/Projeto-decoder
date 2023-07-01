package com.ead.authuser.services.impl;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private CourseClient courseClient;

  @Override
  public List<UserModel> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<UserModel> findById(UUID userId) {
    return userRepository.findById(userId);
  }


  @Override
  public void save(UserModel userModel) {
    userRepository.save(userModel);
  }

  @Override
  public boolean existsByusername(String username) {
    return userRepository.existsByusername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
    return userRepository.findAll(spec, pageable);
  }


}

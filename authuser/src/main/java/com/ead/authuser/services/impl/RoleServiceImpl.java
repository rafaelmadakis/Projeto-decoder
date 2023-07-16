package com.ead.authuser.services.impl;

import com.ead.authuser.models.RoleModel;
import com.ead.authuser.repositories.RoleRepository;
import com.ead.authuser.services.RoleService;
import com.ead.authuser.unums.RoleType;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;


  @Override
  public Optional<RoleModel> findByRoleName(RoleType roleType) {
    return roleRepository.findByRoleName(roleType);
  }
}

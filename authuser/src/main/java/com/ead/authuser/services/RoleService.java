package com.ead.authuser.services;

import com.ead.authuser.models.RoleModel;
import com.ead.authuser.unums.RoleType;
import java.util.Optional;

public interface RoleService {

  Optional<RoleModel> findByRoleName(RoleType roleType);

}

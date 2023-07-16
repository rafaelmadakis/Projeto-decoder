package com.ead.authuser.repositories;

import com.ead.authuser.models.RoleModel;
import com.ead.authuser.unums.RoleType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

  Optional<RoleModel> findByRoleName(RoleType name);

}

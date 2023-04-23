package com.ead.authuser.repositories;

import com.ead.authuser.models.UserModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<UserModel, UUID>,
                                                    JpaSpecificationExecutor<UserModel> {


  boolean existsByusername(String username);

  boolean existsByEmail(String email);
}

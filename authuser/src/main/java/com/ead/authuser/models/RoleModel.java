package com.ead.authuser.models;

import com.ead.authuser.unums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "TB_ROLES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel implements GrantedAuthority, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID roleId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, unique = true, length = 30)
  private RoleType roleName;

  @Override
  @JsonIgnore
  public String getAuthority() {
    return this.roleName.toString();
  }
}

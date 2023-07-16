package com.ead.authuser.models;

import com.ead.authuser.dtos.UserEventDto;
import com.ead.authuser.unums.UserStatus;
import com.ead.authuser.unums.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

@Data
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
public class UserModel extends RepresentationModel<UserModel> implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID userId;

  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @Column(nullable = false, unique = true, length = 50)
  private String email;

  @Column(nullable = false, length = 255)
  @JsonIgnore
  private String password;

  @Column(nullable = false, length = 150)
  private String fullName;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserStatus userStatus;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserType userType;

  @Column(length = 20)
  private String phoneNumber;

  @Column(length = 20)
  private String cpf;

  @Column
  private String imageUrl;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  @Column(nullable = false)
  private LocalDateTime creationDate;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  @Column(nullable = false)
  private LocalDateTime lastUpdateDate;


  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "TB_USERS_ROLES",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleModel> roles = new HashSet<>();


  public UserEventDto convertToUserEventDto() {
    var userEventDto = new UserEventDto();
    BeanUtils.copyProperties(this, userEventDto);
    userEventDto.setUserType(this.getUserType().toString());
    userEventDto.setUserStatus(this.getUserStatus().toString());
    return userEventDto;
  }
}

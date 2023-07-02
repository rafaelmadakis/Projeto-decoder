package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private UUID userId;

  @Column(nullable = false, unique = true, length = 50)
  private String email;

  @Column(nullable = false, length = 150)
  private String fullName;

  @Column(nullable = false)
  private String userStatus;

  @Column(nullable = false)
  private  String userType;

  @Column(length = 20)
  private String cpf;

  @Column
  private String imageUrl;

  @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
  @JsonProperty(access = Access.WRITE_ONLY)
  private Set<CourseModel> courses;


}

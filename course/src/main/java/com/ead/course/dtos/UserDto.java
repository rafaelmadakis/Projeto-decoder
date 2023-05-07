package com.ead.course.dtos;

import com.ead.course.models.UserStatus;
import com.ead.course.models.UserType;
import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {

  private UUID userId;

  private String username;

  private String email;

  private String fullName;

  private UserStatus userStatus;

  private UserType userType;

  private String phoneNumber;

  private String cpf;

  private String imageUrl;

}

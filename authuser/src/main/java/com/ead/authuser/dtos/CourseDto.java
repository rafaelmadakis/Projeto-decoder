package com.ead.authuser.dtos;

import com.ead.authuser.unums.CourseLevel;
import com.ead.authuser.unums.CourseStatus;
import java.util.UUID;
import lombok.Data;

@Data
public class CourseDto {

  private UUID courseId;

  private String name;

  private String description;

  private String imageUrl;

  private CourseStatus courseStatus;

  private UUID userInstructor;

  private CourseLevel courseLevel;

}

package com.ead.authuser.controllers;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.services.UserService;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController {

  private CourseClient userClient;

  public UserCourseController(CourseClient userClient) {
    this.userClient = userClient;
  }

  @Autowired
  private UserService userService;


  @PreAuthorize("hasAnyRole('STUDENT')")
  @GetMapping(value = "/users/{userId}/courses")
  public ResponseEntity<Page<CourseDto>> getAllCoursesByUser(@PageableDefault(page = 0, size = 10,
      sort = "courseId", direction = Direction.ASC) Pageable pageable,
      @PathVariable(value = "userId") UUID userId, @RequestHeader("Authorization") String token) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(userClient.getAllCoursesByUser(userId, pageable, token));

  }
}

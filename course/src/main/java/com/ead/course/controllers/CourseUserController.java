package com.ead.course.controllers;

import com.ead.course.dtos.SubscriptionDto;
import com.ead.course.dtos.UserDto;
import com.ead.course.enums.UserStatus;
import com.ead.course.models.CourseModel;
import com.ead.course.models.UserModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.UserService;
import com.ead.course.specifications.SpecificationTemplate;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseUserController {

  @Autowired
  private CourseService courseService;

  @Autowired
  private UserService userService;

  @GetMapping(value = "/courses/{courseId}/users")
  public ResponseEntity<Object> getAllUsersByCourse(SpecificationTemplate.UserSpec spec,
      @PageableDefault(page = 0, size = 10,
      sort = "userId", direction = Direction.ASC) Pageable pageable,
      @PathVariable(value = "courseId") UUID courseId) {

    Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
    if (!courseModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found");
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(userService.findAll(SpecificationTemplate.userCourseId(courseId).and(spec), pageable));
  }

  @PostMapping("/courses/{courseId}/users/subscription")
  public  ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "courseId") UUID courseId,
      @RequestBody @Valid SubscriptionDto subscriptionDto){
    Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
    if(!courseModelOptional.isPresent()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
    }
    if(courseService.existsByCourseAndUser(courseId, subscriptionDto.getUserId())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: subscription already exists!");
    }
    Optional<UserModel> userModelOptional = userService.findById(subscriptionDto.getUserId());
    if(!userModelOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    if(userModelOptional.get().getUserStatus().equals(UserStatus.BLOCKED.toString())){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("User is blocked.");
    }
//    courseService.saveSubscriptionUserInCourse(courseModelOptional.get().getCourseId(),
//        userModelOptional.get().getUserId());
//    return ResponseEntity.status(HttpStatus.CREATED).body("Subscription created successfully.");

    courseService.saveSubscriptionUserInCourseAndSendNotification(courseModelOptional.get(),
        userModelOptional.get());

    return ResponseEntity.status(HttpStatus.CREATED).body("Subscription created successfully.");
  }
}

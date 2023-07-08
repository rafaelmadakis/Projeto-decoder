package com.ead.controllers;

import com.ead.notification.models.NotificationModel;
import com.ead.notification.services.NotificationService;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserNotificationController {

  final NotificationService notificationService;

  public UserNotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping("/users/{userId}/notifications")
  public ResponseEntity<Page<NotificationModel>> getAllNotificationsByUser(
      @PathVariable(value = "userId")
      UUID userId,
      @PageableDefault(page = 0, size = 10, sort = "notification)", direction = Direction.ASC)
      Pageable pageable) {

    return ResponseEntity.status(HttpStatus.OK).body(notificationService
        .findAllNotificationByUser(userId, pageable));
  }

}

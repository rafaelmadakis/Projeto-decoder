package com.ead.notification.models;

import com.ead.notification.enums.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "TB_NOTIFICATION")
public class NotificationModel {
  private static final long serialVersionUID = 1;


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID notificationId;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false, length = 150)
  private String title;

  @Column(nullable = false)
  private String message;

  @Column(nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  private LocalDateTime creationDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private NotificationStatus notificationStatus;

  public UUID getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(UUID notificationId) {
    this.notificationId = notificationId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public NotificationStatus getNotificationStatus() {
    return notificationStatus;
  }

  public void setNotificationStatus(NotificationStatus notificationStatus) {
    this.notificationStatus = notificationStatus;
  }


}

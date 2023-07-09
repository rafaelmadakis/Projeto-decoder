package com.ead.notification.services;

import com.ead.notification.models.NotificationModel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

  NotificationModel saveNotification(NotificationModel notificationModel);

  Page<NotificationModel> findAllNotificationByUser(UUID userId, Pageable pageable);

  Optional<NotificationModel> findByNotificationIdAndUserId(UUID notificationId, UUID userId);


}

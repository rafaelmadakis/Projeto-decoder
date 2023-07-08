package com.ead.notification.services;

import com.ead.notification.models.NotificationModel;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

  NotificationModel saveNotification(NotificationModel notificationModel);

  Object findAllNotificationByUser(UUID userId, Pageable pageable);
}

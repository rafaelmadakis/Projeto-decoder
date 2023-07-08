package com.ead.notification.repositories;

import com.ead.notification.models.NotificationModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<NotificationModel, UUID> {

}

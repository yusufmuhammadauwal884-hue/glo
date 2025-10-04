package com.glovendorproject.glovendor_backend_dev.repo;


import com.glovendorproject.glovendor_backend_dev.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Fetch all notifications for a specific recipient
    List<Notification> findByRecipientIdAndRecipientType(Integer recipientId, Notification.RecipientType recipientType);

    // Fetch only unread notifications
    List<Notification> findByRecipientIdAndRecipientTypeAndReadFalse(Integer recipientId, Notification.RecipientType recipientType);
}

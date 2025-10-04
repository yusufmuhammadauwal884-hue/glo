package com.glovendorproject.glovendor_backend_dev.service;


import com.glovendorproject.glovendor_backend_dev.entity.Notification;
import com.glovendorproject.glovendor_backend_dev.repo.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Send a notification
    public Notification sendNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Get all notifications for a recipient
    public List<Notification> getNotifications(Integer recipientId, Notification.RecipientType recipientType) {
        return notificationRepository.findByRecipientIdAndRecipientType(recipientId, recipientType);
    }

    // Get unread notifications only
    public List<Notification> getUnreadNotifications(Integer recipientId, Notification.RecipientType recipientType) {
        return notificationRepository.findByRecipientIdAndRecipientTypeAndReadFalse(recipientId, recipientType);
    }

    // Mark a notification as read
    public Notification markAsRead(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            Notification n = notification.get();
            n.setRead(true);
            return notificationRepository.save(n);
        } else {
            throw new RuntimeException("Notification not found with id " + id);
        }
    }

    // Delete a notification
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}

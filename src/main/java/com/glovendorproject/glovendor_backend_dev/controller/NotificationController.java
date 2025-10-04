package com.glovendorproject.glovendor_backend_dev.controller;


import com.glovendorproject.glovendor_backend_dev.entity.Notification;
import com.glovendorproject.glovendor_backend_dev.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Send a new notification
    @PostMapping("/add")
    public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.sendNotification(notification));
    }

    // Get all notifications for a specific recipient
    @GetMapping("/{recipientType}/{recipientId}")
    public ResponseEntity<List<Notification>> getNotifications(
            @PathVariable Notification.RecipientType recipientType,
            @PathVariable Integer recipientId) {
        return ResponseEntity.ok(notificationService.getNotifications(recipientId, recipientType));
    }

    // Get only unread notifications
    @GetMapping("/{recipientType}/{recipientId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(
            @PathVariable Notification.RecipientType recipientType,
            @PathVariable Integer recipientId) {
        return ResponseEntity.ok(notificationService.getUnreadNotifications(recipientId, recipientType));
    }

    // Mark as read
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    // Delete a notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}

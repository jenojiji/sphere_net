package com.personal.sphere_net.service.notification;

import com.personal.sphere_net.model.Notification;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.model.enums.EventType;
import com.personal.sphere_net.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void createNotification(User targetUser, User sourceUser, EventType eventType, String message) {
        Notification notification = Notification.builder()
                .targetUser(targetUser)
                .sourceUser(sourceUser)
                .eventType(eventType)
                .message(message)
                .build();
        System.out.println("******notification service******");
        notificationRepository.save(notification);
    }
}

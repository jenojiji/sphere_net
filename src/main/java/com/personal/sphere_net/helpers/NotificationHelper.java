package com.personal.sphere_net.helpers;

import com.personal.sphere_net.event.NotificationEvent;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.model.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationHelper {
    private final ApplicationEventPublisher eventPublisher;

    public void sendNotification(Object source, User recipient, User actor, EventType eventType, String message) {
        NotificationEvent notificationEvent = new NotificationEvent(source, recipient, actor, eventType, message);
        eventPublisher.publishEvent(notificationEvent);
    }
}

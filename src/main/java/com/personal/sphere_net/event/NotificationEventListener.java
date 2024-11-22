package com.personal.sphere_net.event;

import com.personal.sphere_net.service.notification.NotificationService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener implements ApplicationListener<NotificationEvent> {

    private final NotificationService notificationService;

    public NotificationEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(NotificationEvent event) {
        System.out.println("******notification event listener******");
        notificationService.createNotification(
                event.getTargetUser(),
                event.getSourceUser(),
                event.getEventType(),
                event.getMessage());

    }
}

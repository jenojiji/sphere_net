package com.personal.sphere_net.event;

import com.personal.sphere_net.model.User;
import com.personal.sphere_net.model.enums.EventType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class NotificationEvent extends ApplicationEvent {


    private User targetUser;
    private User sourceUser;
    private EventType eventType;
    private String message;

    public NotificationEvent(Object source, User targetUser, User sourceUser, EventType eventType, String message) {
        super(source);
        this.targetUser = targetUser;
        this.sourceUser = sourceUser;
        this.eventType = eventType;
        this.message = message;
        System.out.println("******notification event******");
    }

    public NotificationEvent(Object source, Clock clock) {
        super(source, clock);
    }

}

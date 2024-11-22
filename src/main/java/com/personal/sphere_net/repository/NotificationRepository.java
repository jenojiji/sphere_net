package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}

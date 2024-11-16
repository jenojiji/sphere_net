package com.personal.sphere_net.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notification_id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
    }
}

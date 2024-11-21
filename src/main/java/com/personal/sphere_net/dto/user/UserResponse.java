package com.personal.sphere_net.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long user_id;
    private String username;
    private String email;
    private String profile_picture;
    private String bio;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

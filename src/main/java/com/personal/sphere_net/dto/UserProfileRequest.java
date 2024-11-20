package com.personal.sphere_net.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequest {
    private String username;
    private String profile_picture;
    private String bio;
}

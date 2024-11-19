package com.personal.sphere_net.mapper;

import com.personal.sphere_net.dto.UserResponse;
import com.personal.sphere_net.model.User;

public final class UserMapper {

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .email(user.getEmail())
                .profile_picture(user.getProfile_picture())
                .bio(user.getBio())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build();
    }
}

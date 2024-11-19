package com.personal.sphere_net.mapper;

import com.personal.sphere_net.model.Like;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;

public final class LikeMapper {


    public static Like toLike(Post post, User user) {
        return Like.builder()
                .post(post)
                .user(user)
                .build();
    }
}

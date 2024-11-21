package com.personal.sphere_net.mapper;

import com.personal.sphere_net.dto.like.LikeResponse;
import com.personal.sphere_net.model.Comment;
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

    public static Like toLike(Post post, User user, Comment comment) {
        return Like.builder()
                .post(post)
                .user(user)
                .comment(comment)
                .build();
    }


    public static LikeResponse toLikeResponse(Like like) {
        return LikeResponse.builder()
                .like_id(like.getLike_id())
                .user_id(like.getUser().getUser_id())
                .username(like.getUser().getUsername())
                .post_id(like.getPost().getPost_id())
                .comment_id(like.getComment().getComment_id())
                .build();
    }
}

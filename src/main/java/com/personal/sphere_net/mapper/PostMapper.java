package com.personal.sphere_net.mapper;

import com.personal.sphere_net.dto.PostRequest;
import com.personal.sphere_net.dto.PostResponse;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;

public final class PostMapper {

    public static Post toPost(PostRequest request, User user){
        return Post.builder()
                .content(request.getContent())
                .media_url(request.getMedia_url())
                .user(user)
                .build();
    }

    public static PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .post_id(post.getPost_id())
                .content(post.getContent())
                .mediaUrl(post.getMedia_url())
                .user_id(post.getUser().getUser_id())
                .username(post.getUser().getUsername())
                .created_at(post.getCreated_at())
                .updated_at(post.getUpdated_at())
                .build();
    }
}

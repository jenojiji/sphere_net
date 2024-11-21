package com.personal.sphere_net.mapper;

import com.personal.sphere_net.dto.post.PostRequest;
import com.personal.sphere_net.dto.post.PostResponse;
import com.personal.sphere_net.model.Hashtag;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public final class PostMapper {

    public static Post toPost(PostRequest request, User user, Set<Hashtag> hashtags) {
        return Post.builder()
                .content(request.getContent())
                .media_url(request.getMedia_url())
                .user(user)
                .hashtags(hashtags)
                .build();
    }

    public static PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .post_id(post.getPost_id())
                .content(post.getContent())
                .mediaUrl(post.getMedia_url())
                .user_id(post.getUser().getUser_id())
                .username(post.getUser().getUsername())
                .hashtags(post.getHashtags().stream().map(HashtagMapper::toHashtagResponse).collect(Collectors.toSet()))
                .created_at(post.getCreated_at())
                .updated_at(post.getUpdated_at())
                .build();
    }
}

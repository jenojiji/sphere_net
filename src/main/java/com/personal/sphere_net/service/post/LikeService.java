package com.personal.sphere_net.service.post;

import com.personal.sphere_net.mapper.LikeMapper;
import com.personal.sphere_net.model.Like;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.LikeRepository;
import com.personal.sphere_net.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public String likePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("No post found with id :" + postId)
        );
        User user = post.getUser();
        Like like = LikeMapper.toLike(post, user);
        likeRepository.save(like);
        return "Liked";
    }

    public String dislikePost(Long postId) {
        Optional<Like> like=likeRepository.
    }
}

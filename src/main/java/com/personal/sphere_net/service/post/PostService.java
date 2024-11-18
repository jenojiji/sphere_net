package com.personal.sphere_net.service.post;

import com.personal.sphere_net.dto.PostRequest;
import com.personal.sphere_net.dto.PostResponse;
import com.personal.sphere_net.mapper.PostMapper;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.PostRepository;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public PostResponse createPost(@Valid PostRequest request) {
        User user = userRepository.findById(request.getUser_id()).orElseThrow(()
                -> new EntityNotFoundException("User not found with userId:"
                + request.getUser_id()));
        Post newPost = PostMapper.toPost(request, user);
        Post savedPost = postRepository.save(newPost);
        return PostMapper.toPostResponse(savedPost);


    }

    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()
                -> new EntityNotFoundException("Post not found with post_id:" + postId));
        return PostMapper.toPostResponse(post);
    }

    public PostResponse updatePostById(Long postId, PostRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(()
                -> new EntityNotFoundException("Post not found with post_id:" + postId));
        post.setContent(request.getContent());
        post.setMedia_url(request.getMedia_url());
        Post updatedPost = postRepository.save(post);
        return PostMapper.toPostResponse(updatedPost);
    }

    public String deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()
                -> new EntityNotFoundException("Post not found with post_id:" + postId));
        postRepository.delete(post);
        return "Deleted";
    }

    public Page<PostResponse> getPostsByUserId(Long user_id, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> result = postRepository.findByUserUserId(user_id, pageable);
        return result.map(PostMapper::toPostResponse);
    }

    public Page<PostResponse> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> result = postRepository.findAll(pageable);
        return result.map(PostMapper::toPostResponse);
    }
}

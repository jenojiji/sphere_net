package com.personal.sphere_net.controller.post;

import com.personal.sphere_net.dto.post.PostRequest;
import com.personal.sphere_net.dto.post.PostResponse;
import com.personal.sphere_net.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //save new post
    @PostMapping()
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostRequest request) {

        PostResponse postResponse = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    //view existing post
    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponse> viewPost(@PathVariable Long post_id) {
        PostResponse postResponse = postService.getPostById(post_id);
        return ResponseEntity.status(HttpStatus.OK).body(postResponse);
    }

    //update existing post
    @PutMapping("/{post_id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long post_id,
                                                   @RequestBody PostRequest request) {
        PostResponse postResponse = postService.updatePostById(post_id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(postResponse);
    }

    //delete a post by post_id
    @DeleteMapping("/{post_id}")
    public ResponseEntity<String> deletePost(@PathVariable Long post_id) {
        String postResponse = postService.deletePostById(post_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(postResponse);
    }

    // get all posts of a user
    @GetMapping("/users/{user_id}")
    public ResponseEntity<Page<PostResponse>> getPostsByUser(
            @PathVariable Long user_id,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostResponse> products = postService.getPostsByUserId(user_id, pageNo, pageSize);
        return ResponseEntity.ok(products);
    }

    //get all posts
    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<PostResponse> products = postService.getAllPosts(pageNo, pageSize);
        return ResponseEntity.ok(products);
    }

    //get all posts by hashtag
    @GetMapping("/hashtags")
    public Page<PostResponse> getAllPostsByHashtag(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {

        return postService.getAllPostsByHashtag(searchTerm,pageNo, pageSize);
    }

}

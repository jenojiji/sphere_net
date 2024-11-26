package com.personal.sphere_net.controller.post;

import com.personal.sphere_net.dto.like.LikeResponse;
import com.personal.sphere_net.service.post.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("posts/{post_id}/like")
    public ResponseEntity<String> likePost(@PathVariable Long post_id, @RequestParam Long user_id) {
        return ResponseEntity.ok(likeService.likePost(post_id, user_id));
    }

    @DeleteMapping("posts/{post_id}/like")
    public ResponseEntity<String> dislikePost(@PathVariable Long post_id, @RequestParam Long user_id) {
        return ResponseEntity.ok(likeService.dislikePost(post_id, user_id));
    }

    @PostMapping("comments/{comment_id}/like")
    public ResponseEntity<String> likeComment(@PathVariable Long comment_id, @RequestParam Long user_id) {
        return ResponseEntity.ok(likeService.likeComment(comment_id, user_id));
    }

    @DeleteMapping("comments/{comment_id}/like")
    public ResponseEntity<String> dislikeComment(@PathVariable Long comment_id) {
        return ResponseEntity.ok(likeService.dislikeComment(comment_id));
    }

    @GetMapping("posts/{post_id}/like")
    public ResponseEntity<List<LikeResponse>> getAllLikesOfPost(@PathVariable Long post_id) {
        return ResponseEntity.ok(likeService.getAllLikesOfPost(post_id));
    }

    @GetMapping("comments/{comment_id}/like")
    public ResponseEntity<List<LikeResponse>> getAllLikesOfComment(@PathVariable Long comment_id) {
        return ResponseEntity.ok(likeService.getAllLikesOfComment(comment_id));
    }

}

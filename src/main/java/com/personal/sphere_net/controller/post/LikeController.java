package com.personal.sphere_net.controller.post;

import com.personal.sphere_net.service.post.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("posts/{post_id}/like")
    public ResponseEntity<String> likePost(@PathVariable Long post_id) {
        return ResponseEntity.ok(likeService.likePost(post_id));
    }

    @DeleteMapping("posts/{post_id}/like")
    public ResponseEntity<String> dislikePost(@PathVariable Long post_id) {
        return ResponseEntity.ok(likeService.dislikePost(post_id));
    }

}

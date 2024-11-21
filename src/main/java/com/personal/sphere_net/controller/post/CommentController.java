package com.personal.sphere_net.controller.post;

import com.personal.sphere_net.dto.comment.CommentRequest;
import com.personal.sphere_net.dto.comment.CommentResponse;
import com.personal.sphere_net.service.post.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // add top-level and reply comment
    @PostMapping("/posts/comments")
    public ResponseEntity<CommentResponse> addCommentToPost
    (@RequestBody CommentRequest request) {
        CommentResponse response = commentService.createCommentToPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // delete comment
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long comment_id) {
        return ResponseEntity.ok(commentService.deleteCommentById(comment_id));
    }

    // update comment
    @PutMapping("/{comment_id}")
    public CommentResponse updateComment(@PathVariable Long comment_id, @RequestBody String content) {
        return commentService.updateCommentById(comment_id, content);
    }

    //get all comments of  a post
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<Page<CommentResponse>> getAllCommentsOfPost(
            @PathVariable Long post_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CommentResponse> responses = commentService.getAllCommentsOfPost(post_id, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    //get all replies of a comment
    @GetMapping("/{comment_id}/replies")
    public ResponseEntity<Page<CommentResponse>> getAllRepliesOfComment(
            @PathVariable Long comment_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CommentResponse> responses = commentService.getAllRepliesOfComment(comment_id, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}

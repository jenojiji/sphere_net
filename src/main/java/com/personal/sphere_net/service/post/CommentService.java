package com.personal.sphere_net.service.post;

import com.personal.sphere_net.dto.CommentRequest;
import com.personal.sphere_net.dto.CommentResponse;
import com.personal.sphere_net.mapper.CommentMapper;
import com.personal.sphere_net.model.Comment;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.CommentRepository;
import com.personal.sphere_net.repository.PostRepository;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;


    // add top-level and reply comment
    public CommentResponse createCommentToPost(CommentRequest request) {
        Post post = postRepository.findById(request.getPost_id())
                .orElseThrow(() -> new EntityNotFoundException
                        ("No post with id :" + request.getPost_id()));

        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(() -> new EntityNotFoundException("No user found with userId :" + request.getUser_id()));
        Comment newComment;
        if (request.getParent_comment_id() != null) {
            Comment parentComment = commentRepository.findById(request.getParent_comment_id()).orElseThrow(
                    () -> new EntityNotFoundException("Parent Comment with id :" + request.getParent_comment_id()
                            + " not found")
            );
            newComment = CommentMapper.toComment(request, user, post, parentComment);
        } else {
            newComment = CommentMapper.toComment(request, user, post);
        }
        Comment savedComment = commentRepository.save(newComment);
        return CommentMapper.toCommentResponse(savedComment);

    }

    public String deleteCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("No comment with id :" + commentId + " found in the db")
        );
        commentRepository.delete(comment);
        return "Deleted successfully";
    }

    public Page<CommentResponse> getAllCommentsOfPost(Long postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentRepository.findByPostIdAndParentCommentIsNull(postId, pageable);
        return comments.map((CommentMapper::toCommentResponse));
    }

    public Page<CommentResponse> getAllRepliesOfComment(Long commentId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentRepository.findByParentCommentId(commentId, pageable);
        return comments.map((CommentMapper::toCommentResponse));
    }
}

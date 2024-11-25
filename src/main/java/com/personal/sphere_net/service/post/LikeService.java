package com.personal.sphere_net.service.post;

import com.personal.sphere_net.dto.like.LikeResponse;
import com.personal.sphere_net.helpers.NotificationHelper;
import com.personal.sphere_net.helpers.NotificationMessageBuilder;
import com.personal.sphere_net.mapper.LikeMapper;
import com.personal.sphere_net.model.Comment;
import com.personal.sphere_net.model.Like;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.model.enums.EventType;
import com.personal.sphere_net.repository.CommentRepository;
import com.personal.sphere_net.repository.LikeRepository;
import com.personal.sphere_net.repository.PostRepository;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final NotificationHelper notificationHelper;


    public String likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("No post found with id :" + postId)
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with userId:"
                        + userId)
        );
        Like like = LikeMapper.toLike(post, user);
        likeRepository.save(like);

        notificationHelper.sendNotification(this, post.getUser(), user, EventType.LIKE,
                NotificationMessageBuilder.BuildLikeMessageForPost(user));
        return "Post Liked";
    }

    public String dislikePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("No post found with id :" + postId)
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with userId:"
                        + userId)
        );
        Optional<Like> like = likeRepository.findByPostIdAndUserId(postId, user.getUser_id());
        if (like.isPresent()) {
            likeRepository.delete(like.get());
            return "Post Disliked";
        } else {
            throw new RuntimeException("USER has not liked the post yet");
        }
    }

    public String likeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Comment not found with commentId :" + commentId)
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with ID :" + userId)
        );
        Like like = LikeMapper.toLike(comment.getPost(), user, comment);
        likeRepository.save(like);
        notificationHelper.sendNotification(this, comment.getUser(), user, EventType.LIKE,
                NotificationMessageBuilder.BuildLikeMessageForComment(user));
        return "Comment Liked";
    }

    public String dislikeComment(Long commentId) {
        Optional<Like> like = likeRepository.findByCommentId(commentId);
        if (like.isPresent()) {
            likeRepository.delete(like.get());
            return "Comment Disliked";
        } else {
            throw new RuntimeException("USER has not liked the comment yet");
        }
    }


    public List<LikeResponse> getAllLikesOfPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("No post found with id :" + postId)
        );
        return post.getLikes().stream().map(LikeMapper::toLikeResponse).collect(Collectors.toList());
    }

    public List<LikeResponse> getAllLikesOfComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Comment not found with commentId :" + commentId)
        );
        return comment.getLikes().stream().map(LikeMapper::toLikeResponse).collect(Collectors.toList());
    }
}

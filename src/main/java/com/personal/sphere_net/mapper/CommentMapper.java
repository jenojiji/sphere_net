package com.personal.sphere_net.mapper;

import com.personal.sphere_net.dto.CommentRequest;
import com.personal.sphere_net.dto.CommentResponse;
import com.personal.sphere_net.model.Comment;
import com.personal.sphere_net.model.Post;
import com.personal.sphere_net.model.User;

import java.util.stream.Collectors;

public final class CommentMapper {
    public static Comment toComment(CommentRequest request, User user, Post post, Comment parentComment) {
        return Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .parent_comment(parentComment)
                .build();
    }

    public static Comment toComment(CommentRequest request, User user, Post post) {
        return Comment.builder()
                .content(request.getContent())
                .post(post)
                .user(user)
                .build();
    }

    public static CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .content(comment.getContent())
                .post(PostMapper.toPostResponse(comment.getPost()))
                .user(UserMapper.toUserResponse(comment.getUser()))
                .parent_comment_id(comment.getParent_comment() != null ? comment.getParent_comment().getComment_id() : null)
                .replies(comment.getReplies() != null ? comment.getReplies().stream().map(
                        CommentMapper::toCommentResponse).collect(Collectors.toList()) : null)
                .build();
    }
}

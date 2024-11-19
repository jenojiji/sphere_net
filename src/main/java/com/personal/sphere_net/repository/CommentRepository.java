package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT c FROM Comment c WHERE c.post.post_id=:postId AND c.parent_comment.comment_id IS NULL")
    Page<Comment> findByPostIdAndParentCommentIsNull(@Param("postId") Long postId, Pageable pageable);

    @Query(value = "SELECT c FROM Comment c WHERE c.parent_comment.comment_id=:comment_id")
    Page<Comment> findByParentCommentId(@Param("comment_id") Long comment_id, Pageable pageable);
}

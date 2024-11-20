package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query(value = "SELECT l FROM Like l WHERE l.user.user_id=:userId AND l.post.post_id=:postId")
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    @Query(value = "SELECT l FROM Like l WHERE l.comment.comment_id=:commentId")
    Optional<Like> findByCommentId(Long commentId);
}

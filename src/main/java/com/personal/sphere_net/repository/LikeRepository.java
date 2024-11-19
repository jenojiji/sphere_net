package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);
}

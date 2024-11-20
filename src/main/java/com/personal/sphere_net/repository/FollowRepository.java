package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT f FROM Follow f WHERE f.follower.user_id=:followerId AND f.followed.user_id=:followedId")
    Optional<Follow> findFollowByFollowerIdAndFollowedId(Long followerId, Long followedId);
}

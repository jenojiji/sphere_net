package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Follow;
import com.personal.sphere_net.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    @Query("SELECT f FROM Follow f WHERE f.follower.user_id=:followerId AND f.followed.user_id=:followedId")
    Optional<Follow> findFollowByFollowerIdAndFollowedId(Long followerId, Long followedId);

    @Query(value = "SELECT f.follower FROM Follow f WHERE f.followed=:userId")
    List<User> findFollowers(Long userId);

    @Query(value = "SELECT f.followed FROM Follow f WHERE f.follower=:userId")
    List<User> findFollowing(Long userId);
}

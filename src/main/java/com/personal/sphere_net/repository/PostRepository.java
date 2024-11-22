package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT p FROM Post p JOIN p.user u WHERE u.user_id = :user_id")
    Page<Post> findByUserUserId(@Param("user_id") Long user_id, Pageable pageable);

}

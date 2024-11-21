package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM User u WHERE u.username=:username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(u.bio) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> findUsersBySearchTerm(@Param("searchTerm") String searchTerm);
}

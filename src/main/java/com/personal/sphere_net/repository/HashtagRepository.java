package com.personal.sphere_net.repository;

import com.personal.sphere_net.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    @Query("SELECT h FROM Hashtag h WHERE LOWER(h.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Hashtag> findHashtagsBySearchTerm(String searchTerm);

    @Query("SELECT h FROM Hashtag h WHERE LOWER(h.name) = LOWER(:searchTerm)")
    Optional<Hashtag> findByHashtagName(String searchTerm);
}

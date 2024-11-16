package com.personal.sphere_net.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hashtags")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtag_id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "hashtags")
    private List<Post> posts;


}

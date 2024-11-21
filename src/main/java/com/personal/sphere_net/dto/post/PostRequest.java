package com.personal.sphere_net.dto.post;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    private String content;
    private String media_url;
    private Long user_id;
    private Set<Long> hashtagIds;
}

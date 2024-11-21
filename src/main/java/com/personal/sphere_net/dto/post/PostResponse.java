package com.personal.sphere_net.dto.post;

import com.personal.sphere_net.dto.hashtag.HashtagResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long post_id;
    private String content;
    private String mediaUrl;
    private Long user_id;
    private String username;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Set<HashtagResponse> hashtags;

}

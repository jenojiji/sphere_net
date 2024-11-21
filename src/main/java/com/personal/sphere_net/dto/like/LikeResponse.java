package com.personal.sphere_net.dto.like;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponse {
    private Long like_id;
    private Long user_id;
    private String username;
    private Long post_id;
    private Long comment_id;
}

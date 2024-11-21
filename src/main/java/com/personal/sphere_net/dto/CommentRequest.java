package com.personal.sphere_net.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {
    private String content;
    private Long post_id;
    private Long user_id;
    private Long parent_comment_id;
}

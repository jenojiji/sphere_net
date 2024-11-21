package com.personal.sphere_net.dto.comment;

import com.personal.sphere_net.dto.user.UserResponse;
import com.personal.sphere_net.dto.post.PostResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private String content;
    private PostResponse post;
    private UserResponse user;
    private Long parent_comment_id;
    private List<CommentResponse> replies;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}

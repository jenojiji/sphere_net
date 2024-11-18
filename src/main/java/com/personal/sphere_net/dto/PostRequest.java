package com.personal.sphere_net.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    private String content;
    private String media_url;
    private Long user_id;
}

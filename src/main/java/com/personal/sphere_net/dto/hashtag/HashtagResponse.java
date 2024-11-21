package com.personal.sphere_net.dto.hashtag;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HashtagResponse {
    private Long hashtag_id;
    private String name;
}

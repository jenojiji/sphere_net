package com.personal.sphere_net.mapper;

import com.personal.sphere_net.dto.hashtag.HashtagResponse;
import com.personal.sphere_net.model.Hashtag;

public final class HashtagMapper {

    public static HashtagResponse toHashtagResponse(Hashtag hashtag) {
        return HashtagResponse.builder()
                .hashtag_id(hashtag.getHashtag_id())
                .name(hashtag.getName())
                .build();
    }
}

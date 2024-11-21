package com.personal.sphere_net.service;

import com.personal.sphere_net.dto.hashtag.HashtagResponse;
import com.personal.sphere_net.mapper.HashtagMapper;
import com.personal.sphere_net.model.Hashtag;
import com.personal.sphere_net.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public HashtagResponse saveHashtag(String hashtag) {
        Hashtag newHashtag = Hashtag.builder()
                .name(hashtag)
                .build();
        Hashtag savedHashtag = hashtagRepository.save(newHashtag);
        return HashtagMapper.toHashtagResponse(savedHashtag);
    }

    public List<HashtagResponse> searchHashtagBySearchTerm(String searchTerm) {
        List<Hashtag> responses = hashtagRepository.findUsersBySearchTerm(searchTerm);
        return responses.stream().map(HashtagMapper::toHashtagResponse).toList();
    }
}

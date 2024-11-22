package com.personal.sphere_net.service.hashtag;

import com.personal.sphere_net.dto.hashtag.HashtagResponse;
import com.personal.sphere_net.mapper.HashtagMapper;
import com.personal.sphere_net.model.Hashtag;
import com.personal.sphere_net.repository.HashtagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public HashtagResponse saveHashtag(String hashtag) {
        Optional<Hashtag> existingHashtag = hashtagRepository.findByHashtagName(hashtag);
        if (existingHashtag.isPresent()) {
            throw new EntityNotFoundException("Hashtag with name :" + hashtag + " :doesn't exist");
        } else {
            Hashtag newHashtag = Hashtag.builder()
                    .name(hashtag)
                    .build();
            Hashtag savedHashtag = hashtagRepository.save(newHashtag);
            return HashtagMapper.toHashtagResponse(savedHashtag);
        }
    }

    public List<HashtagResponse> searchHashtagBySearchTerm(String searchTerm) {
        List<Hashtag> responses = hashtagRepository.findHashtagsBySearchTerm(searchTerm);
        return responses.stream().map(HashtagMapper::toHashtagResponse).toList();
    }
}

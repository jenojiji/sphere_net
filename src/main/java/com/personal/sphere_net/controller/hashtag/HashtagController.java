package com.personal.sphere_net.controller.hashtag;

import com.personal.sphere_net.dto.hashtag.HashtagResponse;
import com.personal.sphere_net.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hashtags")
@RequiredArgsConstructor
public class HashtagController {

    private final HashtagService hashtagService;

    @PostMapping
    public HashtagResponse saveHashtag(@RequestParam String hashtag) {
        return hashtagService.saveHashtag(hashtag);
    }

    @GetMapping
    public List<HashtagResponse> getAllHashtagsBySearchTerm(@RequestParam String searchTerm) {
        return hashtagService.searchHashtagBySearchTerm(searchTerm);
    }

}

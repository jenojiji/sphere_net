package com.personal.sphere_net.service.user;

import com.personal.sphere_net.dto.UserProfileRequest;
import com.personal.sphere_net.dto.UserResponse;
import com.personal.sphere_net.mapper.UserMapper;
import com.personal.sphere_net.model.Follow;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.FollowRepository;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public UserResponse updateProfile(Long userId, UserProfileRequest profileRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with Id :" + userId));

        user.setUsername(profileRequest.getUsername());
        user.setProfile_picture(profileRequest.getProfile_picture());
        user.setBio(profileRequest.getBio());
        User savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }

    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with Id :" + userId));
        return UserMapper.toUserResponse(user);
    }

    public UserResponse getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return UserMapper.toUserResponse(user.get());
        } else {
            throw new EntityNotFoundException("User not found with username :" + username);
        }
    }

    public String followUser(Long followerId, Long followedId) {
        User follower = userRepository.findById(followerId).orElseThrow(
                () -> new EntityNotFoundException("User not found with Id :" + followerId));
        User followed = userRepository.findById(followedId).orElseThrow(
                () -> new EntityNotFoundException("User not found with Id :" + followedId));
        Follow follow = Follow.builder()
                .follower(follower)
                .followed(followed)
                .build();
        followRepository.save(follow);
        return "Followed";
    }

    public String unfollowUser(Long followerId, Long followedId) {
        Optional<Follow> follow = followRepository
                .findFollowByFollowerIdAndFollowedId(followerId, followedId);
        if (follow.isPresent()) {
            followRepository.delete(follow.get());
            return "Unfollowed";
        } else {
            throw new EntityNotFoundException("User is not followed yet");
        }
    }

    public List<UserResponse> getAllFollowers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with Id :" + userId)
        );
        List<User> followers = followRepository.findFollowers(userId);
        return followers.stream().map(UserMapper::toUserResponse).toList();
    }

    public List<UserResponse> getAllFollowings(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with Id :" + userId)
        );
        List<User> followings = followRepository.findFollowing(userId);
        return followings.stream().map(UserMapper::toUserResponse).toList();
    }
}

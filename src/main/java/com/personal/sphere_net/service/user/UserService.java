package com.personal.sphere_net.service.user;

import com.personal.sphere_net.dto.UserProfileRequest;
import com.personal.sphere_net.dto.UserResponse;
import com.personal.sphere_net.mapper.UserMapper;
import com.personal.sphere_net.model.User;
import com.personal.sphere_net.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
}

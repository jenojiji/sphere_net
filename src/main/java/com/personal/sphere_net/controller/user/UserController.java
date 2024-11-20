package com.personal.sphere_net.controller.user;

import com.personal.sphere_net.dto.UserProfileRequest;
import com.personal.sphere_net.dto.UserResponse;
import com.personal.sphere_net.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("users/{user_id}/profile")
    public ResponseEntity<UserResponse> updateProfile(@PathVariable Long user_id, UserProfileRequest profileRequest) {
        return ResponseEntity.ok(userService.updateProfile(user_id, profileRequest));
    }

    @GetMapping("users/{user_id}/profile")
    public ResponseEntity<UserResponse> viewProfile(@PathVariable Long user_id) {
        return ResponseEntity.ok(userService.getUserProfile(user_id));
    }

}

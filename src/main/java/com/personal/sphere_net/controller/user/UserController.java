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

    //update user profile
    @PostMapping("/{user_id}/profile")
    public ResponseEntity<UserResponse> updateProfile(@PathVariable Long user_id, UserProfileRequest profileRequest) {
        return ResponseEntity.ok(userService.updateProfile(user_id, profileRequest));
    }

    //view user profile
    @GetMapping("/{user_id}/profile")
    public ResponseEntity<UserResponse> viewProfile(@PathVariable Long user_id) {
        return ResponseEntity.ok(userService.getUserProfile(user_id));
    }

    //search a user by username
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    // follow another user
    @PostMapping("/{follower_id}/follow")
    public ResponseEntity<String> followUser(@PathVariable Long follower_id, @RequestParam Long followed_id) {
        return ResponseEntity.ok(userService.followUser(follower_id, followed_id));
    }

    // unfollow another user
    @PostMapping("/{follower_id}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable Long follower_id, @RequestParam Long followed_id) {
        return ResponseEntity.ok(userService.unfollowUser(follower_id, followed_id));
    }

}

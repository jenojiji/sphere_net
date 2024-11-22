package com.personal.sphere_net.helpers;

import com.personal.sphere_net.model.User;

public final class NotificationMessageBuilder {

    public static String BuildLikeMessage(User actor) {
        return actor.getUsername() + " liked your post";
    }

    public static String BuildCommentMessage(User user, String comment) {
        return user.getUsername() + " commented on your post: " + comment;
    }

    public static String BuildFollowMessage(User follower) {
        return follower.getUsername() + " started following you!";
    }
}

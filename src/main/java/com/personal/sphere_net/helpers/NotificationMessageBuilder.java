package com.personal.sphere_net.helpers;

import com.personal.sphere_net.model.User;

public final class NotificationMessageBuilder {

    public static String BuildLikeMessageForPost(User actor) {
        return actor.getUsername() + " liked your post";
    }

    public static String BuildCommentMessage(User user, String comment) {
        return user.getUsername() + " commented on your post: " + comment;
    }

    public static String BuildFollowMessage(User follower) {
        return follower.getUsername() + " started following you!";
    }

    public static String BuildLikeMessageForComment(User user) {
        return user.getUsername() + " liked your comment";
    }

    public static String BuildReplyCommentMessage(User user, String content) {
        return user.getUsername() + " replied to your comment : " + content;
    }
}

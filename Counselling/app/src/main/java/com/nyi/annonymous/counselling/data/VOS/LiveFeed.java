package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class LiveFeed {
    private String name;
    private String post;
    private int likeCount;
    private String commentID;

    public LiveFeed(String name, String post, int likeCount, String commentID) {
        this.name = name;
        this.post = post;
        this.likeCount = likeCount;
        this.commentID = commentID;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public String getCommentID() {
        return commentID;
    }
}

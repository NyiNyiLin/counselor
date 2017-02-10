package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class LiveFeed {
    private String name;
    private String post;
    private int likeCount;
    private String commentID;
    private boolean isAnnou;

    private String ID;

    public LiveFeed() {
    }

    public LiveFeed(String ID, String name, String post, int likeCount, String commentID, boolean isAnnou) {
        this.name = name;
        this.post = post;
        this.likeCount = likeCount;
        this.commentID = commentID;
        this.isAnnou = isAnnou;
        this.ID = ID;
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

    public String getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setAnnou(boolean annou) {
        isAnnou = annou;
    }

    public boolean isAnnou() {
        return isAnnou;
    }
}

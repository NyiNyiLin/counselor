package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 02-Mar-17.
 */

public class Comment {
    String name;
    String photoURL;
    String comment;

    public Comment() {
    }

    public Comment(String name, String photoURL, String comment) {
        this.name = name;
        this.photoURL = photoURL;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getComment() {
        return comment;
    }
}

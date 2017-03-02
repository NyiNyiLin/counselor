package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 02-Mar-17.
 */

public class UserVO {
    String id;
    String name;
    String email;
    String photoURL;

    public UserVO() {
    }

    public UserVO(String id, String name, String email, String photoURL) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photoURL = photoURL;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}

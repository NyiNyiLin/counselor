package com.nyi.annonymous.counselling.data.VOS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class User {
    private String name;
    private String comment;
    private String chat;

    public User() {
    }

    public User(String name, String comment, String chat) {
        this.name = name;
        this.comment = comment;
        this.chat = chat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getChat() {
        return chat;
    }
}

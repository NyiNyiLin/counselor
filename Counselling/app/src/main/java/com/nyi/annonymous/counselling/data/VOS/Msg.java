package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class Msg {
    private String message;
    private String name;

    public Msg() {
    }

    public Msg(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }
}

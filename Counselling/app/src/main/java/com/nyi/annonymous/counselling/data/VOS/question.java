package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 11-Feb-17.
 */

public class Question {
    private String title;
    private String text;
    private String next;
    private String back;

    public Question(String title, String text, String next, String back) {
        this.title = title;
        this.text = text;
        this.next = next;
        this.back = back;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getNext() {
        return next;
    }

    public String getBack() {
        return back;
    }
}

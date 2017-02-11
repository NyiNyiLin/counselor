package com.nyi.annonymous.counselling.data.VOS;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class MsgList {
    private String name1;
    private String name2;
    private String chatID;

    public MsgList(String name1, String name2, String chatID) {
        this.name1 = name1;
        this.name2 = name2;
        this.chatID = chatID;
    }

    public MsgList() {
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getChatID() {
        return chatID;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setChatID(String chatID) {
        this.chatID = chatID;
    }
}

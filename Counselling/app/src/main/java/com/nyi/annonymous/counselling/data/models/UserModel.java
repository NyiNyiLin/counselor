package com.nyi.annonymous.counselling.data.models;

import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.data.VOS.User;

/**
 * Created by IN-3442 on 30-Nov-16.
 */

public class UserModel {

    private static UserModel objInstance;

    private boolean isSignIn = false;
    private User user;
    private MsgList msgList;

    private UserModel(){
    }

    public static UserModel objInstance(){
        if(objInstance == null) objInstance = new UserModel();
        return objInstance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public MsgList getMsgList() {
        return msgList;
    }

    public void setMsgList(MsgList msgList) {
        this.msgList = msgList;
    }
}

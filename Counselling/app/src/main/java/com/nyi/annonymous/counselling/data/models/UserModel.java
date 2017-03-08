package com.nyi.annonymous.counselling.data.models;

import com.google.firebase.auth.FirebaseUser;
import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.data.VOS.User;
import com.nyi.annonymous.counselling.data.VOS.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 30-Nov-16.
 */

public class UserModel {

    private static UserModel objInstance;

    private boolean isSignIn = false;
    private User user;
    private MsgList msgList;

    private List<UserVO> userList = new ArrayList<>();
    private FirebaseUser firebaseUser;

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

    public void addNewUser(UserVO user){
        userList.add(user);
    }

    public List<UserVO> getUserList() {
        return userList;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        isSignIn = true;
        this.firebaseUser = firebaseUser;
    }

    public String[] getUserNameList(){

        int size = userList.size();
        String userNameList[] = new String[size];

        for(int a = 0; a < size; a++){
            userNameList[a] = userList.get(a).getName();
        }
        return userNameList;
    }

}

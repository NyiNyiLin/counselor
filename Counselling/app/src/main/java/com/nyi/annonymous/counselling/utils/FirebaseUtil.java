package com.nyi.annonymous.counselling.utils;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.data.VOS.User;
import com.nyi.annonymous.counselling.data.models.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class FirebaseUtil {
    private static FirebaseUtil objInstance;
    private DatabaseReference mDatabase;

    private FirebaseUtil(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public static FirebaseUtil getObjInstance(){
        if(objInstance == null) objInstance = new FirebaseUtil();
        return objInstance;
    }

    public DatabaseReference getDatabaseReference() {
        return mDatabase;
    }

    public void uploadPost(){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        LiveFeed liveFeed1 = new LiveFeed("aa", "N", "sjfhasjkdhfdkj", 3, "commentID", true);
        LiveFeed liveFeed2 = new LiveFeed("aa", "N1", "sjfhasjkdhfdkj", 3, "commentID", false);
        LiveFeed liveFeed3 = new LiveFeed("aa", "N2", "sjfhasjkdhfdkj", 3, "commentID", true);
        LiveFeed liveFeed4 = new LiveFeed("aa", "N3", "sjfhasjkdhfdkj", 3, "commentID", false);
        LiveFeed liveFeed5 = new LiveFeed("aa", "N4", "sjfhasjkdhfdkj", 3, "commentID", false);
        LiveFeed liveFeed6 = new LiveFeed("aa", "N5", "sjfhasjkdhfdkj", 3, "commentID", false);
        mDatabase.child(Constants.REF_LIVEFEED).push().setValue(liveFeed1);
        mDatabase.child(Constants.REF_LIVEFEED).push().setValue(liveFeed2);
        mDatabase.child(Constants.REF_LIVEFEED).push().setValue(liveFeed3);
        mDatabase.child(Constants.REF_LIVEFEED).push().setValue(liveFeed4);
        mDatabase.child(Constants.REF_LIVEFEED).push().setValue(liveFeed5);
        mDatabase.child(Constants.REF_LIVEFEED).push().setValue(liveFeed6);


    }

    public void uploadUser(String userName, String password){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child(Constants.REF_USER).child(userName).setValue(new User(userName, password));
    }

    public void post(String feeling, boolean isAnnoy){
        //Post Comment
        DatabaseReference databaseReferenceComment = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_COMMENT);
        String commentID = databaseReferenceComment.push().getKey();

        //Post LiveFeed
        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_LIVEFEED);
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(new LiveFeed(key, UserModel.objInstance().getUser().getName(), feeling, 0, commentID, isAnnoy));

        DatabaseReference databaseReference1 = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_USER).child(UserModel.objInstance().getUser().getName()).child(Constants.REF_POST);
        databaseReference1.push().setValue(key);
    }

    public String StartChat(String username1, String username2){
        DatabaseReference mDatabase1 = FirebaseDatabase.getInstance().getReference().child(Constants.REF_USER).child(username1).child("chat");
        DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference().child(Constants.REF_USER).child(username2).child("chat");

        mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.REF_CHAT);
        String key = mDatabase.push().getKey();

        MsgList msgList = new MsgList(username1, username2, key);
        mDatabase1.push().setValue(msgList);
        mDatabase2.push().setValue(msgList);

        mDatabase.child(key).push().setValue(new Msg("Hi", username1));

        UserModel.objInstance().setMsgList(msgList);
        return key;
    }

}

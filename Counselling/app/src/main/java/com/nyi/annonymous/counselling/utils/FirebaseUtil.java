package com.nyi.annonymous.counselling.utils;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.VOS.User;

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

    public void uploadUser(){
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child(Constants.REF_USER).child("Myat").setValue(new User("Myat", "comment", "chat"));
    }

}

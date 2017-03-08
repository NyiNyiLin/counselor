package com.nyi.annonymous.counselling;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class Counselling extends Application{
    private static Context context;
    FirebaseAuth firebaseAuth;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //FacebookSdk.sdkInitialize(getApplicationContext());

        AssetManager am = context.getApplicationContext().getAssets();

        //titleTypeface = Typeface.createFromAsset(getAssets(),  "fonts/CoreSansGRounded-Medium.ttf");
        //textTypeface = Typeface.createFromAsset(getAssets(),  "fonts/CoreSansG-Regular.ttf");
        //FirebaseUtil.getObjInstance().uploadTestMenu();
        //FirebaseUtil.getObjInstance().uploadTestShop();

        FirebaseUtil.getObjInstance().getUserList();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null) UserModel.objInstance().setFirebaseUser(user);
    }

    public static Context getContext() {
        return context;
    }
/*
    public static Typeface getTitleTypeface() {
        return titleTypeface;
    }

    public static Typeface getTextTypeface() {
        return textTypeface;
    }*/
}

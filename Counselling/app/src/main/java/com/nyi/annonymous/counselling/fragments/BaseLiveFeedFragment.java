package com.nyi.annonymous.counselling.fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.activities.LogInfbActivity;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;

/**
 * Created by IN-3442 on 04-Mar-17.
 */

public class BaseLiveFeedFragment extends Fragment {

    void likeFunc(LiveFeed liveFeed){
        checkLogIn();

        int like = liveFeed.getLikeCount();

        liveFeed.setLikeCount(like+1);

        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_LIVEFEED).child(liveFeed.getID());
        databaseReference.setValue(liveFeed).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("Councellor", "LiveFeeedFragment onTapLike like complete");

            }
        });
    }

    void commentFunc(LiveFeed liveFeed){
        checkLogIn();

        CommentDialogFragment dialog = CommentDialogFragment.newInstance(liveFeed.getCommentID());
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
        dialog.show(getFragmentManager(), "Comment");
    }

    void checkLogIn(){
        if(!UserModel.objInstance().isSignIn()){
            Intent intent = LogInfbActivity.newIntent();
            startActivity(intent);
        }
    }
}

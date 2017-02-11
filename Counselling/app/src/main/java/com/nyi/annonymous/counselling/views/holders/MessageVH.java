package com.nyi.annonymous.counselling.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.data.models.UserModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class MessageVH extends RecyclerView.ViewHolder {
    public TextView messageTextView;

    public MessageVH(View v) {
        super(v);
        messageTextView = (TextView) itemView.findViewById(R.id.messageTextView);
    }

    public void bindData(Msg msg){
        if(UserModel.objInstance().getUser().getName().compareTo(msg.getName()) == 0) messageTextView.setGravity(Gravity.RIGHT);
        else messageTextView.setGravity(Gravity.LEFT);

            messageTextView.setText(msg.getMessage());
    }
}


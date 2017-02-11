package com.nyi.annonymous.counselling.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.data.models.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class MsgListVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_msg_list_name)
    TextView name;

    private MsgListControl msgListControl;
    private MsgList msgList;

    public MsgListVH(View itemView, final MsgListControl msgListControl) {
        super(itemView);
        this.msgListControl = msgListControl;
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgListControl.onTapList(msgList);
            }
        });
    }

    public void bindData(MsgList msgList){
        this.msgList = msgList;

        if(msgList.getName1().compareTo(UserModel.objInstance().getUser().getName()) == 0) name.setText(msgList.getName2());
        else name.setText(msgList.getName1());
    }

    public interface MsgListControl{
        void onTapList(MsgList msgList);
    }
}

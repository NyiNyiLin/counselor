package com.nyi.annonymous.counselling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.views.holders.MessageVH;
import com.nyi.annonymous.counselling.views.holders.MsgListVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MsgAdapter extends RecyclerView.Adapter<MessageVH> {
    private LayoutInflater inflater;
    private List<Msg> msgList = new ArrayList<>();
    private MsgListVH.MsgListControl controllerMsgList;

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
        inflater = LayoutInflater.from(Counselling.getContext());
    }


    @Override
    public MessageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_message, parent, false);
        MessageVH msgListVH = new MessageVH(view);

        return msgListVH;
    }

    @Override
    public void onBindViewHolder(MessageVH holder, int position) {
        holder.bindData(msgList.get(position));
    }



    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public void addNewMessage(Msg msg){
        msgList.add(msg);
        notifyDataSetChanged();

    }

}

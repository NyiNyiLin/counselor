package com.nyi.annonymous.counselling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.views.holders.LiveFeedVH;
import com.nyi.annonymous.counselling.views.holders.MsgListVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MsgListAdapter extends RecyclerView.Adapter<MsgListVH> {
    private LayoutInflater inflater;
    private List<MsgList> msgList = new ArrayList<>();
    private MsgListVH.MsgListControl controllerMsgList;

    public MsgListAdapter(List<MsgList> msgList, MsgListVH.MsgListControl controllerMsgList) {
        this.msgList = msgList;
        this.controllerMsgList = controllerMsgList;
        inflater = LayoutInflater.from(Counselling.getContext());
    }


    @Override
    public MsgListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_msg, parent, false);
        MsgListVH msgListVH = new MsgListVH(view, controllerMsgList);

        return msgListVH;
    }

    @Override
    public void onBindViewHolder(MsgListVH holder, int position) {
        holder.bindData(msgList.get(position));
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    public void addNewMenu(MsgList msgList1){
        msgList.add(msgList1);
        notifyDataSetChanged();

    }

}

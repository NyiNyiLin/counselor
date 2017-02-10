package com.nyi.annonymous.counselling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Counsellor;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.views.holders.LeaderBoardVH;
import com.nyi.annonymous.counselling.views.holders.LiveFeedVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardVH> {
    private LayoutInflater inflater;
    private List<Counsellor> mCounsellorList = new ArrayList<>();
    private LeaderBoardVH.LeaderBroadControl control;

    public LeaderBoardAdapter(List<Counsellor> liveFeedList, LeaderBoardVH.LeaderBroadControl controll) {
        this.mCounsellorList = liveFeedList;
        this.control = controll;
        inflater = LayoutInflater.from(Counselling.getContext());
    }

    @Override
    public LeaderBoardVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_counsellor, parent, false);
        LeaderBoardVH leaderBoardVH = new LeaderBoardVH(view, control);

        return leaderBoardVH;
    }

    @Override
    public void onBindViewHolder(LeaderBoardVH holder, int position) {
        holder.bindData(mCounsellorList.get(position));
    }



    @Override
    public int getItemCount() {
        return mCounsellorList.size();
    }

    public void addNewMenu(Counsellor counsellor){
        mCounsellorList.add(counsellor);
        notifyDataSetChanged();

    }

}

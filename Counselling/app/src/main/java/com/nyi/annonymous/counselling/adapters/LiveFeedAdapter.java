package com.nyi.annonymous.counselling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.views.holders.LiveFeedVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class LiveFeedAdapter extends RecyclerView.Adapter<LiveFeedVH> {
    private LayoutInflater inflater;
    private List<LiveFeed> mLiveFeedList = new ArrayList<>();
    private LiveFeedVH.LiveFeedControl controllerLiveFeed;

    public LiveFeedAdapter(List<LiveFeed> liveFeedList, LiveFeedVH.LiveFeedControl controllerLiveFeed) {
        this.mLiveFeedList = liveFeedList;
        this.controllerLiveFeed = controllerLiveFeed;
        inflater = LayoutInflater.from(Counselling.getContext());
    }


    @Override
    public LiveFeedVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_live_feed, parent, false);
        LiveFeedVH liveFeedVH = new LiveFeedVH(view, controllerLiveFeed);

        return liveFeedVH;
    }

    @Override
    public void onBindViewHolder(LiveFeedVH holder, int position) {
        holder.bindData(mLiveFeedList.get(position));
    }



    @Override
    public int getItemCount() {
        return mLiveFeedList.size();
    }

    public void addNewMenu(LiveFeed liveFeed){
        mLiveFeedList.add(liveFeed);
        notifyDataSetChanged();

    }

    public void removeMenu(int position){
        mLiveFeedList.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteAllMenu(){
        mLiveFeedList.clear();
        notifyDataSetChanged();
    }
}

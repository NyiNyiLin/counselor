package com.nyi.annonymous.counselling.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class LiveFeedVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_item_view_live_feed_name)
    TextView name;

    @BindView(R.id.tv_item_view_live_feed_post)
    TextView tvPost;

    @BindView(R.id.tv_iv_live_ffed_like)
    TextView tvLike;

    @BindView(R.id.tv_live_feed_comment)
    TextView tvComment;


    private LiveFeedControl liveFeedControl;
    private LiveFeed liveFeed;

    public LiveFeedVH(View itemView, final LiveFeedControl liveFeedControl) {
        super(itemView);
        this.liveFeedControl = liveFeedControl;
        ButterKnife.bind(this, itemView);

        tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvLike.setText("Vote - " +(liveFeed.getLikeCount()+1));
                liveFeedControl.onTapLike(liveFeed);
            }
        });
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liveFeedControl.onTapComment(liveFeed);
            }
        });

    }

    public void bindData(LiveFeed liveFeed){
        this.liveFeed = liveFeed;
        name.setText(liveFeed.getName());
        tvPost.setText(liveFeed.getPost());
        tvLike.setText("Vote - " +liveFeed.getLikeCount());

        if(liveFeed.isAnnou()){
            name.setText("Anonymouus");
        }
    }

    public interface LiveFeedControl{
        void onTapLike(LiveFeed liveFeed);
        void onTapComment(LiveFeed liveFeed);
    }
}

package com.nyi.annonymous.counselling.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Counsellor;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class LeaderBoardVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_coun_name)
    TextView tvname;

    @BindView(R.id.tv_coun_position)
    TextView tvposition;

    @BindView(R.id.tv_coun_price)
    TextView tvprice;

    @BindView(R.id.iv_chat)
    ImageView ivChat;


    private LeaderBroadControl leaderBroadControl;
    private Counsellor counsellor;

    public LeaderBoardVH(View itemView, final LeaderBroadControl leaderBroadControl) {
        super(itemView);
        this.leaderBroadControl = leaderBroadControl;
        ButterKnife.bind(this, itemView);

        ivChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaderBroadControl.onTapMsg();
            }
        });
    }

    public void bindData(Counsellor counsellor){
        this.counsellor = counsellor;
        tvname.setText(counsellor.getName());
        tvposition.setText(counsellor.getPosition());
        tvprice.setText(counsellor.getPrice() + "KS");

    }

    public interface LeaderBroadControl{
        void onTapMsg();
    }
}

package com.nyi.annonymous.counselling.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Counsellor;
import com.nyi.annonymous.counselling.data.VOS.MyCounsellor;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 10-Feb-17.
 */

public class MyCounsellorVH extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_my_name)
    TextView tvname;

    @BindView(R.id.tv_my_position)
    TextView tvposition;

    @BindView(R.id.tv_my_descrip)
    TextView tvDescrip;

    private MyCounsellor myCounsellor;


    public MyCounsellorVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Counselling.getContext(), "We are contacting with counsellor.  Stay tune.  We will let you know when ready.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void bindData(MyCounsellor counsellor){
        this.myCounsellor = counsellor;
        tvname.setText(myCounsellor.getName());
        tvposition.setText(myCounsellor.getPosition());
        tvDescrip.setText(myCounsellor.getDescrip());

    }
}

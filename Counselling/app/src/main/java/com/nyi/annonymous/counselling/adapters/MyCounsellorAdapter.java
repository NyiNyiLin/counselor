package com.nyi.annonymous.counselling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.Counsellor;
import com.nyi.annonymous.counselling.data.VOS.MyCounsellor;
import com.nyi.annonymous.counselling.views.holders.LeaderBoardVH;
import com.nyi.annonymous.counselling.views.holders.MyCounsellorVH;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 21-Oct-16.
 */

public class MyCounsellorAdapter extends RecyclerView.Adapter<MyCounsellorVH> {
    private LayoutInflater inflater;
    private List<MyCounsellor> myCounsellor = new ArrayList<>();

    public MyCounsellorAdapter(List<MyCounsellor> myCounsellor) {
        this.myCounsellor = myCounsellor;
        inflater = LayoutInflater.from(Counselling.getContext());
    }

    @Override
    public MyCounsellorVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_view_our_counsellor, parent, false);
        MyCounsellorVH myCounsellerVH = new MyCounsellorVH(view);

        return myCounsellerVH;
    }

    @Override
    public void onBindViewHolder(MyCounsellorVH holder, int position) {
        holder.bindData(myCounsellor.get(position));
    }



    @Override
    public int getItemCount() {
        return myCounsellor.size();
    }

    public void addNewMenu(MyCounsellor counsellor){
        myCounsellor.add(counsellor);
        notifyDataSetChanged();

    }

}

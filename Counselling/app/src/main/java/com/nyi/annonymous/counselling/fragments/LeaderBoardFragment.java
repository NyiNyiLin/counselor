package com.nyi.annonymous.counselling.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.adapters.LeaderBoardAdapter;
import com.nyi.annonymous.counselling.data.VOS.Counsellor;
import com.nyi.annonymous.counselling.views.holders.LeaderBoardVH;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaderBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaderBoardFragment extends Fragment implements LeaderBoardVH.LeaderBroadControl{

    @BindView(R.id.rv_leader_board)
    RecyclerView rvLeaderBoard;

    private LeaderBoardAdapter leaderBoardAdapter;
    private List<Counsellor> counsellorList = new ArrayList<>();

    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    public static LeaderBoardFragment newInstance() {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        dummyData();
        leaderBoardAdapter = new LeaderBoardAdapter(counsellorList, this);
    }

    private void dummyData() {
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
        counsellorList.add(new Counsellor("U Kyaw Sar", "Counsellor at Nyi Company", 1200));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leader_board, container, false);
        ButterKnife.bind(this, view);

        rvLeaderBoard.setAdapter(leaderBoardAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Counselling.getContext(), LinearLayoutManager.VERTICAL, false);
        rvLeaderBoard.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onTapMsg() {


    }


}

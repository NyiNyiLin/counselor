package com.nyi.annonymous.counselling.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.activities.ChatActivity;
import com.nyi.annonymous.counselling.activities.LogInActivity;
import com.nyi.annonymous.counselling.adapters.LeaderBoardAdapter;
import com.nyi.annonymous.counselling.data.VOS.Counsellor;
import com.nyi.annonymous.counselling.data.VOS.User;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;
import com.nyi.annonymous.counselling.utils.NetworkUtil;
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
    private List<User> userList;

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
        //dummyData();
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

        getDataFromFirebase();
        return view;
    }

    @Override
    public void onTapMsg(Counsellor counsellor) {
        if(UserModel.objInstance().isSignIn()) {
            if(NetworkUtil.checkInternet(getContext())) {
                String key = FirebaseUtil.getObjInstance().StartChat(UserModel.objInstance().getUser().getName(), counsellor.getName());
                Intent in = ChatActivity.getInstance(key);
                startActivity(in);

            }else{
                Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT);
            }
        }
        else {
            Intent intent = new Intent(getContext(), LogInActivity.class);
            startActivity(intent);
        }
    }

    private void getDataFromFirebase() {
        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_USER);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User userr = dataSnapshot.getValue(User.class);
                Log.d("Counselling", userr.getName());

                leaderBoardAdapter.addNewMenu(new Counsellor(userr.getName(), "", 4));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}

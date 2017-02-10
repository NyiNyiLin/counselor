package com.nyi.annonymous.counselling.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.adapters.LiveFeedAdapter;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;
import com.nyi.annonymous.counselling.views.holders.LiveFeedVH;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiveFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveFeedFragment extends Fragment implements LiveFeedVH.LiveFeedControl{


    @BindView(R.id.rv_live_feed)
    RecyclerView rvLiveFeed;

    private LiveFeedAdapter liveFeedAdapter;
    private List<LiveFeed> liveFeedList = new ArrayList<>();

    public LiveFeedFragment() {
        // Required empty public constructor
    }

    public static LiveFeedFragment newInstance() {
        LiveFeedFragment fragment = new LiveFeedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //dummyData();
        liveFeedAdapter = new LiveFeedAdapter(liveFeedList, this);
    }

    private void dummyData() {
        liveFeedList.add(new LiveFeed("key","Name", "Post", 12, "asas", true));
        liveFeedList.add(new LiveFeed("key","Nyi", "sdhfkjhasjfkd", 12, "asas", false));
        liveFeedList.add(new LiveFeed("key", "Rat", "afsdgdsfgf", 12, "asas", true));
        liveFeedList.add(new LiveFeed("key", "Khin", "dkjfhsjkfhksjhfkjdhjfdhdsjkfhsdkjhgj", 12, "asas", false));
        liveFeedList.add(new LiveFeed("key", "May", "dlkkjsdhgkjhjkghfjkhj", 12, "asas", true));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_feed, container, false);

        ButterKnife.bind(this, view);

        rvLiveFeed.setAdapter(liveFeedAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Counselling.getContext(), LinearLayoutManager.VERTICAL, false);
        rvLiveFeed.setLayoutManager(layoutManager);

        getDataFromFirebase();
        return view;
    }

    @Override
    public void onTapLike() {

    }

    @Override
    public void onTapComment() {

    }

    private void getDataFromFirebase(){
        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_LIVEFEED);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                LiveFeed liveFeed = dataSnapshot.getValue(LiveFeed.class);
                liveFeed.setID(s);
                liveFeedAdapter.addNewMenu(liveFeed);
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

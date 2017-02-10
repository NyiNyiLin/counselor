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
import com.nyi.annonymous.counselling.adapters.LiveFeedAdapter;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
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

        dummyData();
        liveFeedAdapter = new LiveFeedAdapter(liveFeedList, this);
    }

    private void dummyData() {
        liveFeedList.add(new LiveFeed("Name", "Post", 12, "asas"));
        liveFeedList.add(new LiveFeed("Nyi", "sdhfkjhasjfkd", 12, "asas"));
        liveFeedList.add(new LiveFeed("Rat", "afsdgdsfgf", 12, "asas"));
        liveFeedList.add(new LiveFeed("Khin", "dkjfhsjkfhksjhfkjdhjfdhdsjkfhsdkjhgj", 12, "asas"));
        liveFeedList.add(new LiveFeed("May", "dlkkjsdhgkjhjkghfjkhj", 12, "asas"));
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

        return view;
    }

    @Override
    public void onTapLike() {

    }

    @Override
    public void onTapComment() {

    }
}

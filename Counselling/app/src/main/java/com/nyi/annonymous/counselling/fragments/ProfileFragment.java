package com.nyi.annonymous.counselling.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.activities.LogInfbActivity;
import com.nyi.annonymous.counselling.adapters.LiveFeedAdapter;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;
import com.nyi.annonymous.counselling.views.holders.LiveFeedVH;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends BaseLiveFeedFragment implements LiveFeedVH.LiveFeedControl{

    @BindView(R.id.tv_profile_name)
    TextView tvProfileName;

    @BindView(R.id.tv_profile_email)
    TextView tvProfileEmail;

    @BindView(R.id.btn_profile_login)
    Button btnLogIn;

    @BindView(R.id.rv_profile)
    RecyclerView rvProfile;

    @BindView(R.id.rl_profile_main)
    RelativeLayout rlMain;

    @BindView(R.id.ll_profile_log_in)
    LinearLayout llLogIn;

    private LiveFeedAdapter liveFeedAdapter;
    private List<LiveFeed> liveFeedList = new ArrayList<>();
    FirebaseUser user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        liveFeedAdapter = new LiveFeedAdapter(liveFeedList, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        rvProfile.setAdapter(liveFeedAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Counselling.getContext(), LinearLayoutManager.VERTICAL, false);
        rvProfile.setLayoutManager(layoutManager);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserModel.objInstance().isSignIn()){
                    FirebaseAuth.getInstance().signOut();
                    UserModel.objInstance().setSignIn(false);

                }else{
                    Intent intent = LogInfbActivity.newIntent();
                    startActivity(intent);

                }
                //onResume();
                getActivity().recreate();
            }
        });

        return view;
    }

    private void getDataFromFirebase() {

        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_USER).child(user.getUid()).child(Constants.REF_POST);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String postID = dataSnapshot.getValue(String.class);

                getPost(postID);
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

    private void getPost(String postID){
        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_LIVEFEED).child(postID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LiveFeed livefeed = dataSnapshot.getValue(LiveFeed.class);

                liveFeedAdapter.addNewMenu(livefeed);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        if(UserModel.objInstance().isSignIn()){
            user = UserModel.objInstance().getFirebaseUser();
            tvProfileName.setText(user.getDisplayName());
            tvProfileEmail.setText(user.getEmail());

            rlMain.setVisibility(View.VISIBLE);
            llLogIn.setVisibility(View.GONE);

            getDataFromFirebase();
        }else{
            tvProfileName.setText("");
            tvProfileEmail.setText("");

            rlMain.setVisibility(View.GONE);
            llLogIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onTapLike(LiveFeed liveFeed) {
        likeFunc(liveFeed);
    }

    @Override
    public void onTapComment(LiveFeed liveFeed) {
        commentFunc(liveFeed);
    }
}

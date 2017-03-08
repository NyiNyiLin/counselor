package com.nyi.annonymous.counselling.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.activities.ChatActivity;
import com.nyi.annonymous.counselling.activities.LogInActivity;
import com.nyi.annonymous.counselling.activities.LogInfbActivity;
import com.nyi.annonymous.counselling.adapters.LiveFeedAdapter;
import com.nyi.annonymous.counselling.adapters.MsgListAdapter;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.data.VOS.MsgList;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;
import com.nyi.annonymous.counselling.views.holders.LiveFeedVH;
import com.nyi.annonymous.counselling.views.holders.MsgListVH;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment implements MsgListVH.MsgListControl{

    @BindView(R.id.rv_chat)
    RecyclerView rvChat;

    @BindView(R.id.btn_singin)
    Button btnSignIn;

    @BindView(R.id.auto_chat_search)
    AutoCompleteTextView autoChatSearch;

    private MsgListAdapter msgListAdapter;
    private List<MsgList> msgLists = new ArrayList<>();

    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        msgListAdapter = new MsgListAdapter(msgLists, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ButterKnife.bind(this, view);

        rvChat.setAdapter(msgListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Counselling.getContext(), LinearLayoutManager.VERTICAL, false);
        rvChat.setLayoutManager(layoutManager);

        /*if(UserModel.objInstance().isSignIn()) getDataFromFirebase();
        else btnSignIn.setVisibility(View.VISIBLE);*/

        bindlistener();

        return view;
    }

    private void bindlistener() {

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LogInfbActivity.newIntent();
                startActivity(intent);
            }
        });

        ArrayAdapter<String> toAD = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_dropdown_item_1line, UserModel.objInstance().getUserNameList());
        autoChatSearch.setAdapter(toAD);
        autoChatSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                autoChatSearch.showDropDown();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if(UserModel.objInstance().isSignIn()) {
            getDataFromFirebase();
            btnSignIn.setVisibility(View.INVISIBLE);
        }
        else btnSignIn.setVisibility(View.VISIBLE);*/
    }

    private void getDataFromFirebase(){
        final DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_USER).child(UserModel.objInstance().getFirebaseUser().getUid()).child(Constants.REF_CHAT);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MsgList msg = dataSnapshot.getValue(MsgList.class);

                msgLists.add(msg);
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

    @Override
    public void onTapList(MsgList msgList){
        String chatID = msgList.getChatID();
        Intent in = ChatActivity.getInstance(chatID);
        startActivity(in);
    }
}

package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.adapters.MsgAdapter;
import com.nyi.annonymous.counselling.adapters.MsgListAdapter;
import com.nyi.annonymous.counselling.data.VOS.FriendlyMessage;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;
import com.nyi.annonymous.counselling.views.holders.MessageVH;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {
    public static final String  ARG_MSG_ID = "msgid";

    private DatabaseReference mFirebaseDatabaseReference;
    private MsgAdapter msgAdapter;

    @BindView(R.id.messageRecyclerView)
    RecyclerView rvMsg;

    @BindView(R.id.et_message)
    EditText etMessage;

    @BindView(R.id.btn_send)
    Button btnSend;

    List<Msg> msgList = new ArrayList<>();
    String msgID;

    public static Intent getInstance(String messageID){
        Intent in = new Intent(Counselling.getContext(), ChatActivity.class);
        in.putExtra(ARG_MSG_ID, messageID);

        return in;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this, this);

        Intent in = getIntent();
        msgID = in.getStringExtra(ARG_MSG_ID);

        msgAdapter = new MsgAdapter(msgList);
        rvMsg.setAdapter(msgAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(Counselling.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMsg.setLayoutManager(layoutManager);

        msgAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                int friendlyMessageCount = msgAdapter.getItemCount();
                int lastVisiblePosition =
                        layoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    rvMsg.scrollToPosition(positionStart);
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg();
            }
        });

        getDataMsg();

    }

    private void sendMsg() {
        DatabaseReference database = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_CHAT).child(msgID);
        if(etMessage.getText().toString().length() > 0 ) database.push().setValue(new Msg(etMessage.getText().toString(), UserModel.objInstance().getUser().getName()));
        etMessage.setText("");
    }

    private void getDataMsg() {
        final DatabaseReference database = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_CHAT).child(msgID);
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Msg msg = dataSnapshot.getValue(Msg.class);

                msgAdapter.addNewMessage(msg);
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

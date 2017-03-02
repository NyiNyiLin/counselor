package com.nyi.annonymous.counselling.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.adapters.CommentAdapter;
import com.nyi.annonymous.counselling.data.VOS.Comment;
import com.nyi.annonymous.counselling.data.VOS.Msg;
import com.nyi.annonymous.counselling.data.VOS.UserVO;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IN-3442 on 27-Nov-16.
 */

public class CommentDialogFragment extends DialogFragment {

    @BindView(R.id.rvComment)
    RecyclerView rvComment;

    @BindView(R.id.et_comment)
    EditText etComment;

    @BindView(R.id.btn_comment_send)
    Button btnSend;


    private List<Comment> commentList = new ArrayList<>();
    private CommentAdapter comAdapter;
    private String commentID;

    private static final String ARG_COMMENT_ID = "commentID";

    public static CommentDialogFragment newInstance(String commendID){
        CommentDialogFragment commentDialogFragment = new CommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_COMMENT_ID, commendID);
        commentDialogFragment.setArguments(bundle);
        return commentDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        commentID = bundle.getString(ARG_COMMENT_ID);

        comAdapter = new CommentAdapter(commentList);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_comment_dialoge, null);
        ButterKnife.bind(this, view);

        //TODO
        rvComment.setAdapter(comAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Counselling.getContext(), LinearLayoutManager.VERTICAL, false);
        rvComment.setLayoutManager(layoutManager);

        builder.setView(view);

        getDataFromFirebase();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendComment();
            }
        });

        return builder.create();
    }

    @Override
    public void onResume() {

        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();

        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

        //getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        // Call super onResume after sizing
        super.onResume();

    }

    private void getDataFromFirebase() {

        DatabaseReference database = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_COMMENT).child(commentID);
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Comment comment = dataSnapshot.getValue(Comment.class);

                comAdapter.addNewComment(comment);
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

    private void sendComment(){
        DatabaseReference database = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_COMMENT);

        FirebaseUser user = UserModel.objInstance().getFirebaseUser();

        if(etComment.getText().toString().length() > 0 ) database.child(commentID).push().setValue(new Comment(user.getDisplayName(), user.getPhotoUrl().toString(), etComment.getText().toString()));
        etComment.setText("");
    }


/*    Glide.with(YUMenuBookApp.getContext())
            .load(UserModel.objInstance().getPhotoURL())
            .asBitmap().centerCrop()
    .placeholder(R.drawable.ic_camera_black_24dp)
    .error(R.drawable.ic_camera_black_24dp)
    .into(ivPostReviewImage);*/
}

package com.nyi.annonymous.counselling.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.adapters.ViewPagerAdapter;
import com.nyi.annonymous.counselling.data.VOS.LiveFeed;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class PostFragment extends DialogFragment {

    @BindView(R.id.et_feeling)
    EditText etFeeling;

    @BindView(R.id.cb_post_anoy)
    CheckBox cbPostAnnoy;

    @BindView(R.id.btn_post)
    Button btnPost;

    Boolean isAnnoy = false;

    public static PostFragment newInstance() {

        Bundle args = new Bundle();

        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PostFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.fragment_post, null);
        ButterKnife.bind(this, view);


        builder.setView(view);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feeling = etFeeling.getText().toString();

                cbPostAnnoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        isAnnoy = b;
                    }
                });

                if(!feeling.isEmpty()){
                    post(feeling);

                    PostFragment.this.dismiss();
                }

            }
        });

        return builder.create();
    }

    private void post(String feeling){
        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_LIVEFEED);
        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(new LiveFeed("ID", UserModel.objInstance().getUser().getName(), feeling, 0, "comment id", isAnnoy));

        DatabaseReference databaseReference1 = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_USER).child(UserModel.objInstance().getUser().getName()).child(Constants.REF_POST);
        databaseReference1.push().setValue(key);
    }

}

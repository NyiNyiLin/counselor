package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectActivity extends AppCompatActivity {

    @BindView(R.id.ll_ask)
    LinearLayout llask;

    @BindView(R.id.ll_help)
    LinearLayout llHelp;

    @BindView(R.id.ll_share)
    LinearLayout llShare;

    @BindView(R.id.ll_test)
    LinearLayout llTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);

        bindListener();

    }

    private void bindListener() {
        llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Counselling.getContext(), LogInActivity.class);
                startActivity(in);
            }
        });

        llask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Counselling.getContext(), AskActivity.class);
                startActivity(in);
            }
        });

        llTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Counselling.getContext(), TestActivity.class);
                startActivity(in);
            }
        });

        llHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Comming soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

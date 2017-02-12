package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nyi.annonymous.counselling.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AskActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.ll_cv_ep)
    CardView ep;

    @BindView(R.id.ll_cv_health)
    CardView health;

    @BindView(R.id.ll_cv_jp)
    CardView jp;

    @BindView(R.id.ll_cv_l)
    CardView law;

    @BindView(R.id.ll_cv_rp)
    CardView rp;

    @BindView(R.id.ll_cv_sp)
    CardView social;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ep.setOnClickListener(this);
        health.setOnClickListener(this);
        jp.setOnClickListener(this);
        law.setOnClickListener(this);
        rp.setOnClickListener(this);
        social.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MyCounsellorActivity.class);
        startActivity(intent);
    }
}

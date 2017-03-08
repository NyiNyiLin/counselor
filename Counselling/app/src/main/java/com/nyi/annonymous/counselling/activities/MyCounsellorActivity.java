package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.adapters.MyCounsellorAdapter;
import com.nyi.annonymous.counselling.data.VOS.MyCounsellor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCounsellorActivity extends AppCompatActivity {

    @BindView(R.id.rv_my_counseller)
    RecyclerView rvMyCounsellor;

    private List<MyCounsellor> myCounsellorList = new ArrayList<>();
    private MyCounsellorAdapter myCounsellorAdapter;

    public static Intent newIntent(){
        Intent intent = new Intent(Counselling.getContext(), MyCounsellorActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_counsellor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ButterKnife.bind(this, this);

        myCounsellorAdapter = new MyCounsellorAdapter(myCounsellorList);
        rvMyCounsellor.setAdapter(myCounsellorAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Counselling.getContext(), LinearLayoutManager.VERTICAL, false);
        rvMyCounsellor.setLayoutManager(layoutManager);

        getDataFromFirebase();
    }

    private void getDataFromFirebase() {

        myCounsellorAdapter.addNewMenu(new MyCounsellor("Daw Su Zar Mon ", "Teenagers,Adults ", "-suzarmon.psychologist@gmail.com\n" +
                "-master of science in counselling psychology\n" +
                "-works as counselling psychologist\n" +
                "-+95 1 243012\n" +
                "-Panhlaing Siloam Hispital\n"));

        myCounsellorAdapter.addNewMenu(new MyCounsellor("Dr Anila Paul", "Adults ", "-dr anilapaul@spa-mm.com\n" +
                "-Msc and MBBS\n" +
                "-\n"));

        myCounsellorAdapter.addNewMenu(new MyCounsellor("Daw Su Su Maung", "Teenager,Adult,Elderly", "-Clinical Psychologist,Family and Marriage Therapist ,University Psychology Professor \n" +
                "-ssmaung.ma@gmail.com\n" +
                "-Master of arts,Counselling Psychology\n"));

        myCounsellorAdapter.addNewMenu(new MyCounsellor("Dr Nyi Win Hman", "Adult", "-Psychologist \n" +
                "-nyiwinhman@gmail.com\n"));

        myCounsellorAdapter.addNewMenu(new MyCounsellor("Dr Htay Htay", "Teenager", "Work at blah blah blah"));
        myCounsellorAdapter.addNewMenu(new MyCounsellor("U Kyaw Swar", "Health Specialist", "-Psychologist\n" +
                "-htayhtay@gmail.com\nWork at blah blah blah"));


    }

}

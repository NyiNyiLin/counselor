package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.fragments.HomeFragment;
import com.nyi.annonymous.counselling.fragments.PostFragment;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /*@BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(UserModel.objInstance().isSignIn()){
                    DialogFragment postDial = PostFragment.newInstance();
                    postDial.show(getSupportFragmentManager(), "Post");
                }else {
                    Intent intent = LogInfbActivity.newIntent();
                    startActivity(intent);
                }


            }
        });

        /*Menu leftMenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);*/
        //FirebaseUtil.getObjInstance().uploadUser();
        //FirebaseUtil.getObjInstance().StartChat("Nyi", "Myat");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, HomeFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                    Intent intent = LogInfbActivity.newIntent();
                    startActivity(intent);
                return true;
            case android.R.id.home:
                //drawerLayout.openDrawer(GravityCompat.START);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()){
            case R.id.left_menu_home:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, HomeFragment.newInstance())
                        .commit();
                return true;

            case R.id.left_menu_profile:

                return true;
        }

        return false;
    }*/
}

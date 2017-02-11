package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.User;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInActivity extends AppCompatActivity {

    @BindView(R.id.et_login_username)
    EditText userName;

    @BindView(R.id.et_login_password)
    EditText password;

    @BindView(R.id.btn_login)
    Button btnLogin;

    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);

        loadDataFirst();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isLogIn= false;
                for(User user: userList){
                    if(user.getName().compareTo(userName.getText().toString()) == 0 && password.getText().toString().compareTo(user.getPassword()) == 0){
                        UserModel.objInstance().setUser(user);

                        Log.d("Counselling", "user " + user.getName());

                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                    }
                }
                if(!isLogIn) Toast.makeText(getApplicationContext(), "Check User Name and Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataFirst() {
        DatabaseReference databaseReference = FirebaseUtil.getObjInstance().getDatabaseReference().child(Constants.REF_USER);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User userr = dataSnapshot.getValue(User.class);
                Log.d("Counselling", userr.getName());

                userList.add(userr);
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

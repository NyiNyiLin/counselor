package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.utils.FirebaseUtil;
import com.nyi.annonymous.counselling.utils.NetworkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_register_password)
    EditText etPassword;

    @BindView(R.id.et_register_username)
    EditText etUserName;

    @BindView(R.id.tv_log_in)
    TextView tvLogIn;

    @BindView(R.id.btn_register)
    Button btnRegister;

    public static Intent newIntent(){
        Intent in = new Intent(Counselling.getContext(), RegisterActivity.class);
        return in;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this, this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = LogInActivity.newIntent();
                startActivity(in);
            }
        });
    }

    private void register() {


        if(etUserName.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0){

            if(NetworkUtil.checkInternet(getApplicationContext())) {
                FirebaseUtil.getObjInstance().uploadUser(etUserName.getText().toString(), etPassword.getText().toString());

                finish();
            }else{
               Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Plz fill userName and Password", Toast.LENGTH_LONG).show();
        }
    }

}

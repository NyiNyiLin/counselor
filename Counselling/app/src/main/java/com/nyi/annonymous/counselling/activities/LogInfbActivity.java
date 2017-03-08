package com.nyi.annonymous.counselling.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nyi.annonymous.counselling.Counselling;
import com.nyi.annonymous.counselling.R;
import com.nyi.annonymous.counselling.data.VOS.User;
import com.nyi.annonymous.counselling.data.VOS.UserVO;
import com.nyi.annonymous.counselling.data.models.UserModel;
import com.nyi.annonymous.counselling.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInfbActivity extends AppCompatActivity {

    @BindView(R.id.btn_fb_login_log_out)
    Button btnLogOut;

    LoginButton loginButton;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public static Intent newIntent(){
        Intent intent = new Intent(Counselling.getContext(), LogInfbActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_infb);
        ButterKnife.bind(this, this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(Constants.TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    checkUserIsNewOrExisting(user);

                    UserModel.objInstance().setSignIn(true);
                    UserModel.objInstance().setFirebaseUser(user);

                    //finish();
                } else {
                    // User is signed out
                    Log.d(Constants.TAG, "onAuthStateChanged:signed_out");
                    UserModel.objInstance().setSignIn(false);
                }
                logInControl();
                // ...
            }
        };

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        // If using in a fragment
        //loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d(Constants.TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                // App code
                Log.d(Constants.TAG, "facebook:onCancel");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(Constants.TAG, "facebook:onError", exception);

            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
            }
        });
    }

    private void logInControl(){
        if(UserModel.objInstance().isSignIn()){
            loginButton.setVisibility(View.GONE);
            btnLogOut.setVisibility(View.VISIBLE);
        }
        else{
            loginButton.setVisibility(View.VISIBLE);
            btnLogOut.setVisibility(View.GONE);
        }
    }

    private void checkUserIsNewOrExisting(FirebaseUser user) {

        boolean isNew = true;

        List<UserVO> userIDList = UserModel.objInstance().getUserList();

        Log.d(Constants.TAG, "userIDList List " +userIDList.size());

        for(UserVO userID : userIDList){
            if(user.getUid().compareTo(userID.getId()) == 0) {
                isNew = false;
                break;
            }
        }

        if(isNew){
            DatabaseReference mRefernece = FirebaseDatabase.getInstance().getReference().child(Constants.REF_USER_LIST);

            String key = mRefernece.push().getKey();

            UserVO user1 = new UserVO(user.getUid(), user.getDisplayName(), user.getEmail(), user.getPhotoUrl().toString());

            mRefernece.child(key).setValue(user1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(Constants.TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(Constants.TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(Constants.TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LogInfbActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}

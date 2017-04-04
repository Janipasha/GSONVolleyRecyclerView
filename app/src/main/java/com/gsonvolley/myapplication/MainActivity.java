package com.gsonvolley.myapplication;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Jani on 04-04-2017.
 */

public class MainActivity extends AppCompatActivity{

    private LoginButton loginButton;
    private CallbackManager callbackmanager;
    private TextView mloginResult;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.fblogin);

        mloginResult = (TextView)findViewById(R.id.login_display);


        callbackmanager= CallbackManager.Factory.create();

        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               Log.d("User ID: ",loginResult.getAccessToken().getUserId());
                Log.d("Auth Token: " ,loginResult.getAccessToken().getToken());

                Intent intent = new Intent(MainActivity.this,DataActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() {
                mloginResult.setText("Login attempt canceled.");

            }

            @Override
            public void onError(FacebookException error) {

                mloginResult.setText("Login attempt failed.");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackmanager.onActivityResult(requestCode, resultCode, data);
    }

}

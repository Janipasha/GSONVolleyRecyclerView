package com.gsonvolley.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

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

    Toolbar toolbar;
    private LoginButton loginButton;
    private CallbackManager callbackmanager;
    private TextView mloginResult;
    private Button ViewEarthquake;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.fblogin);

        ViewEarthquake = (Button) findViewById(R.id.View_earthquake_button);

        ViewEarthquake.setVisibility(View.GONE);

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

                ViewEarthquake.setVisibility(View.VISIBLE);

                ViewEarthquake.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, DataActivity.class);
                        startActivity(intent);
                    }
                });

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
    protected void onStart() {
        super.onStart();

        ViewEarthquake.setVisibility(View.VISIBLE);

        ViewEarthquake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackmanager.onActivityResult(requestCode, resultCode, data);
    }

}

package com.dtc.testauthgraph.screen;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.dtc.testauthgraph.R;
import com.dtc.testauthgraph.auth.AuthenticationController;


public class MenuApp extends AppCompatActivity {

    private final static String TAG = MenuApp.class.getSimpleName();
    private AuthenticationController authenticationController;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);

        authenticationController = AuthenticationController.getInstance(this);
        result = authenticationController.getAccessToken();

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_visibility));
            actionBar.setTitle("Dashboard");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Result : " + result);
    }

    public void displayCollab(View view){
        Intent intent = new Intent(this, Collaborators.class);
//
//        authenticationController = AuthenticationController.getInstance(this);
//        if(authenticationController != null){
//            result = authenticationController.getAccessToken();
//
//            Log.d(TAG, "Result : " + result);
//            intent.putExtra("TOK", result);
//        }

        startActivity(intent);
    }

    /*-------------------- Cycle de vie Activity ------------------------------------------------*/
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "OnStart called ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume called ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause called ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop called ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy called ");
    }
}

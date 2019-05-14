package com.dtc.testauthgraph.screen;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dtc.testauthgraph.R;
import com.dtc.testauthgraph.auth.AuthenticationController;
import com.dtc.testauthgraph.auth.MSALAuthenticationCallback;


public class MenuApp extends AppCompatActivity {

    private final static String TAG = MenuApp.class.getSimpleName();
    private AuthenticationController authenticationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_visibility));
            actionBar.setTitle("Dashboard");
        }

    }

    public void displayCollab(View view){
        Intent intent = new Intent(this, Collaborators.class);
        Log.d(TAG, "TOKEN" + authenticationController.getAccessToken().toString());
        startActivity(intent);
    }
}

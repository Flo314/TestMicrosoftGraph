package com.dtc.testauthgraph.screen;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dtc.testauthgraph.R;



public class Collaborators extends AppCompatActivity {



    private final static String TAG = Collaborators.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborators);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_visibility));
            actionBar.setTitle("Collaborateurs");
        }

    }

}

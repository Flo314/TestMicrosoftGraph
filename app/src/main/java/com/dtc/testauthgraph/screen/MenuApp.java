package com.dtc.testauthgraph.screen;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dtc.testauthgraph.R;




public class MenuApp extends AppCompatActivity {

    private final static String TAG = MenuApp.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_load));
            actionBar.setTitle("Dashboard");
        }

    }
}

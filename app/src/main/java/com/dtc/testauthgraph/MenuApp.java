package com.dtc.testauthgraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.dtc.testauthgraph.auth.AuthenticationController;

public class MenuApp extends AppCompatActivity {

    AuthenticationController authenticationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);

    }

}

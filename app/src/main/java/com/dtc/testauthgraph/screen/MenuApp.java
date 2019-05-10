package com.dtc.testauthgraph.screen;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dtc.testauthgraph.R;
import com.dtc.testauthgraph.ToolbarFragment;



public class MenuApp extends AppCompatActivity {

    private final static String TAG = MenuApp.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);

//        // on instancie le fragment manager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        // on instancie la transaction grâce au fragment manager
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ToolbarFragment toolbarFragment = new ToolbarFragment();
//        // on remplace le contenu actuel par le fragment 1
//        fragmentTransaction.replace(android.R.id.content,toolbarFragment);
//        fragmentTransaction.commit();
    }
}

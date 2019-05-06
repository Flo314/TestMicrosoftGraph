package com.dtc.testauthgraph;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dtc.testauthgraph.auth.AuthenticationController;

public class MenuApp extends AppCompatActivity {

    AuthenticationController authenticationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);
    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_signout, menu);
        return true;
    }

    // se déconnecter
    private void onSignout() {
        AuthenticationController authenticationController = AuthenticationController.getInstance(this);
        authenticationController.signOut();
    }

    // click de l'item du menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemThatWasClicked = item.getItemId();
        // click icon ... menu (déconnexion)
        if(itemThatWasClicked == R.id.action_menu){
            Context context = MenuApp.this;
            String textToShow = "Add clicked";
            Toast.makeText(this, textToShow, Toast.LENGTH_SHORT).show();
            // se déconnecter
            onSignout();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

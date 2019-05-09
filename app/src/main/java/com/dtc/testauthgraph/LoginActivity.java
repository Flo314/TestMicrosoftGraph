package com.dtc.testauthgraph;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dtc.testauthgraph.auth.AuthenticationController;
import com.dtc.testauthgraph.auth.MSALAuthenticationCallback;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalException;
import com.microsoft.identity.client.User;


public class LoginActivity extends AppCompatActivity implements MSALAuthenticationCallback {

    Button btnsign;
    Button btnload;

    // variable de débug pour le logcat
    private final static String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        // on instancie le fragment manager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        // on instancie la transaction grâce au fragment manager
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ButtonFragmentStartMenu buttonFragmentStartMenu = new ButtonFragmentStartMenu();
//        // on remplace le contenu actuel par le fragment 1
//        fragmentTransaction.replace(android.R.id.content,buttonFragmentStartMenu);
//        fragmentTransaction.commit();

        Button btnload = findViewById(R.id.btnload);
        btnload.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCick fragment ...");
                    Intent intent = new Intent(getApplicationContext(), MenuApp.class);
                    startActivity(intent);
            }
        });

        Button btnsign = findViewById(R.id.btnsign);
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignin();
            }
        });

    }

    // se connecter
    private void onSignin() {
        AuthenticationController authenticationController = AuthenticationController.getInstance(this);
        authenticationController.doAcquireToken(this, this);
        Log.d(TAG, "onSigin : Se connecte ...");
    }

//    // se déconnecter
//    private void onSignout() {
//        AuthenticationController authenticationController = AuthenticationController.getInstance(this);
//        authenticationController.signOut();
//        Log.d(TAG, "onSignout : " + authenticationController);
//    }

    /* quand on veut se connecter on envoi la demande sur le navigateur pour aller prendre l'utilisateur
     jusqu'au point de terminaison d'authentification Azure ad */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // quand le resultat arrive je vérifie ce que j'ai déjà (référence de l'authentification controller)
        if(AuthenticationController.getInstance(this).getPublicClient() != null){
            AuthenticationController.getInstance(this).getPublicClient().handleInteractiveRequestRedirect(requestCode, resultCode ,data);
            Log.d(TAG, "requestCode : " + requestCode + "resultCode : " + resultCode + "data : " + data.getData());
        }
    }

    // récupération du résultat de l'authentification et affichage du user dans un Toast en cas de succés
    @Override
    public void onMsalAuthSuccess(AuthenticationResult authenticationResult) {
        // obtient les infos de l'utilisateur
        User user = authenticationResult.getUser();
        Toast.makeText(LoginActivity.this, "Hello " + user.getName()
                + " (" + user.getDisplayableId() + " " + authenticationResult.getIdToken().toString() + ")", Toast.LENGTH_LONG).show();

    }

    // implémentation des méthode de l'interface MSALAuthenticationCallback pour personnaliser le résultat
    @Override
    public void onMsalAuthError(MsalException exception) {
        Log.e(TAG, "Error authenticated : " + exception, exception);
    }

    @Override
    public void onMsalAuthError(Exception exception) {
        Log.e(TAG, "Error authenticated : " + exception, exception);
    }

    @Override
    public void onMsalAuthCancel() {
        Log.d(TAG, "Cancel authenticated");
    }

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

        Button btnload = findViewById(R.id.btnload);
        btnload.setVisibility(View.VISIBLE);
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

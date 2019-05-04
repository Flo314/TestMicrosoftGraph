package com.dtc.testauthgraph;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalException;
import com.microsoft.identity.client.User;


public class MainActivity extends AppCompatActivity implements MSALAuthenticationCallback{

    private final static String TAG = MainActivity.class.getSimpleName();
    private int progressStatus = 0;
    //private TextView textView;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressBar progressBar = findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);
        //textView = (TextView) findViewById(R.id.textView);

        Button btnsign = findViewById(R.id.btnsign);
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                onSignin();
            }
        });

        Button btnsignout = findViewById(R.id.btnsignout);
        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.GONE);
                onSignout();
            }
        });

        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            //textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void onSignin() {
        AuthenticationController authenticationController = AuthenticationController.getInstance(this);
        authenticationController.doAcquireToken(this, this);
        Log.d(TAG, "onSigin : " + authenticationController);
        //Toast.makeText(MainActivity.this,"Hello <user>",Toast.LENGTH_SHORT).show();
    }

    // se déconnecter
    private void onSignout() {
        AuthenticationController authenticationController = AuthenticationController.getInstance(this);
        authenticationController.signOut();
        Log.d(TAG, "onSigin : " + authenticationController);
        //Toast.makeText(MainActivity.this,"Hello <user>",Toast.LENGTH_SHORT).show();
    }

    /* quand on veut se connecter on envoi la demande sur le navigateur pour aller prendre l'utilisateur
     jusqu'au point de terminaison d'authentification Azure ad */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // quand le resultat arrive je vérifie ce que j'ai déjà
        if(AuthenticationController.getInstance(this).getPublicClient() != null){
            AuthenticationController.getInstance(this).getPublicClient().handleInteractiveRequestRedirect(requestCode, resultCode ,data);
            Log.d(TAG, "requestCode : " + requestCode + "resultCode : " + resultCode + "data : " +data);
        }
    }

    // récupération du résultat de l'authentification
    @Override
    public void onMsalAuthSuccess(AuthenticationResult authenticationResult) {
        User user = authenticationResult.getUser();
        Toast.makeText(MainActivity.this, "Hello" + user.getName()
                + " (" + user.getDisplayableId() + ")", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMsalAuthError(MsalException exception) {
        Log.e(TAG, "Error authenticated" + exception, exception);
    }

    @Override
    public void onMsalAuthError(Exception exception) {
        Log.e(TAG, "Error authenticated" + exception, exception);
    }

    @Override
    public void onMsalAuthCancel() {
        Log.d(TAG, "Cancel authenticated");
    }
}

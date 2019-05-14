package com.dtc.testauthgraph.auth;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalException;
import com.microsoft.identity.client.PublicClientApplication;

/**
 * Contrôleur pour l'authentification
 */
public class AuthenticationController {

    private final String TAG = AuthenticationController.class.getSimpleName();
    private static AuthenticationController INSTANCE;
    private static PublicClientApplication publicClientApplication = null;
    private AuthenticationResult mAuthResult;
    private static Context context;

    private MSALAuthenticationCallback mActivityCallback;

    private AuthenticationController(){}

    // Singleton qui permet soit creer une nouvelle instance du controller ou obtenir un existant
    public static synchronized AuthenticationController getInstance(Context ctx){
        // En passant le context on sauve le context qui est l'activity elle-même
        context = ctx;

        if(INSTANCE == null){
            INSTANCE = new AuthenticationController();
            if(publicClientApplication == null){
                publicClientApplication = new PublicClientApplication(context, Constants.CLIENT_ID);
            }
        }
        return INSTANCE;
    }

    public static synchronized void restInstance(){
        INSTANCE = null;
    }

    public String getAccessToken(){
        return mAuthResult.getAccessToken();
    }

    public PublicClientApplication getPublicClient(){
        return publicClientApplication;
    }

    // Obtention du token dans l'activity en cours
    public void doAcquireToken(Activity activity, final MSALAuthenticationCallback msalCallback){
        // reçoit le jeton par le callback (msalCallback)
        mActivityCallback = msalCallback;
        // utilisation des autorisations (Constants.SCOPE)
        publicClientApplication.acquireToken(activity, Constants.SCOPES, getAuthSilentCallback());
//        Log.d(TAG, "Test : " + publicClientApplication.toString());
    }

    public void signOut(){
        // supprime l'utilisateur actuellement connecté
        publicClientApplication.remove(mAuthResult.getUser());
        // rénitialisation de l'instance (getInstance)
        AuthenticationController.restInstance();
    }

    // Différentes méthodes de callback en cas de résultat ok ou non
    // on récupère le résultat dans chaque méthode (mAuthResult) et on le test
    private AuthenticationCallback getAuthSilentCallback(){
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(AuthenticationResult authenticationResult) {
                mAuthResult = authenticationResult;
                if(mActivityCallback != null){
                    mActivityCallback.onMsalAuthSuccess(mAuthResult);
                    Log.d(TAG, "Success authenticated");
                }
            }

            @Override
            public void onError(MsalException exception) {
                if(mActivityCallback != null){
                    mActivityCallback.onMsalAuthError(exception);
                    Log.d(TAG, "Authentication failed: " + exception.toString());
                }
            }

            @Override
            public void onCancel() {
                if(mActivityCallback != null){
                    mActivityCallback.onMsalAuthCancel();
                    Log.d(TAG, "User cancelled login.");
                }
            }
        };
    }

}

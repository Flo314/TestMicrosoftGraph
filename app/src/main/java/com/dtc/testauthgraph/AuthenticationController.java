package com.dtc.testauthgraph;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.microsoft.identity.client.AuthenticationCallback;
import com.microsoft.identity.client.AuthenticationResult;
import com.microsoft.identity.client.MsalException;
import com.microsoft.identity.client.PublicClientApplication;

public class AuthenticationController {

    private final String TAG = AuthenticationController.class.getSimpleName();
    private static AuthenticationController INSTANCE;
    private static PublicClientApplication publicClientApplication;
    private AuthenticationResult mAuthResult;
    private static Context context;

    private MSALAuthenticationCallback mActivityCallback;

    private AuthenticationController(){}

    public static synchronized AuthenticationController getInstance(Context ctx){
        context = ctx;

        if(INSTANCE == null){
            INSTANCE = new AuthenticationController();
            if(publicClientApplication == null){
                publicClientApplication = new PublicClientApplication(context, Constants.CLIENT_ID);
            }
        }
        return INSTANCE;
    }

    public static synchronized void restInstance(){ INSTANCE = null; }

    public String getAccessToken(){ return mAuthResult.getAccessToken(); }

    public PublicClientApplication getPublicClient(){ return publicClientApplication; }

    public void doAcquireToken(Activity activity, final MSALAuthenticationCallback msalCallback){
        mActivityCallback = msalCallback;
        publicClientApplication.acquireToken(activity, Constants.SCOPES, getAuthInteractiveCallback());
        Log.d(TAG, "Test" + msalCallback);
    }

    public void signOut(){
        publicClientApplication.remove(mAuthResult.getUser());
        AuthenticationController.restInstance();
    }

    private AuthenticationCallback getAuthInteractiveCallback(){
        return new AuthenticationCallback() {
            @Override
            public void onSuccess(AuthenticationResult authenticationResult) {
                mAuthResult = authenticationResult;
                if(mActivityCallback != null){
                    mActivityCallback.onMsalAuthSuccess(mAuthResult);
                    Log.e(TAG, "Error authenticated" + mAuthResult);
                }
            }

            @Override
            public void onError(MsalException exception) {
                if(mActivityCallback != null){
                    mActivityCallback.onMsalAuthError(exception);
                    Log.e(TAG, "Error authenticated" + exception, exception);
                }
            }

            @Override
            public void onCancel() {
                if(mActivityCallback != null){
                    mActivityCallback.onMsalAuthCancel();
                }
            }
        };
    }

}

package com.dtc.testauthgraph.network;

import android.content.Context;
import android.util.Log;

import com.dtc.testauthgraph.auth.AuthenticationController;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.core.DefaultClientConfig;
import com.microsoft.graph.core.IClientConfig;
import com.microsoft.graph.extensions.GraphServiceClient;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.http.IHttpRequest;

/**
 * Ce service permet de creer une nouvelle instance du service graph
 */
public class MSGraphServiceClientManager implements IAuthenticationProvider {

    private final static String TAG = MSGraphServiceClientManager.class.getSimpleName();
    private IGraphServiceClient graphClient;
    private static MSGraphServiceClientManager INSTANCE;
    private static Context context;

    @Override
    public void authenticateRequest(IHttpRequest request) {
        try{
            // ajoute à chaque demande dans l'entête d'autorisation le jeton du AuthenticationController
            request.addHeader("Autorization", "Bearer"
            + AuthenticationController.getInstance(context)
            .getAccessToken());

            Log.i(TAG, "Request: " + request.toString());

        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public static synchronized MSGraphServiceClientManager getInstance(Context ctx){
        context = ctx;
        if(INSTANCE == null){
            INSTANCE = new MSGraphServiceClientManager();
        }
        return INSTANCE;
    }

    // obtenir le graphservice
    public synchronized  IGraphServiceClient getGraphServiceClient(){
        return getGraphServiceClient(this);
    }

    // construction du client graph
    public synchronized IGraphServiceClient getGraphServiceClient(IAuthenticationProvider authenticationProvider){
        if (graphClient == null){
            IClientConfig clientConfig = DefaultClientConfig.createWithAuthenticationProvider(authenticationProvider);
            graphClient = new GraphServiceClient.Builder().fromConfig(clientConfig).buildClient();
        }
        return graphClient;
    }
}

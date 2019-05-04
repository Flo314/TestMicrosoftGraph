package com.dtc.testauthgraph.network;

import android.content.Context;
import android.util.Log;

import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.graph.concurrency.ICallback;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.extensions.Event;
import com.microsoft.graph.extensions.IEventCollectionPage;
import com.microsoft.graph.extensions.IEventCollectionRequest;
import com.microsoft.graph.extensions.IGraphServiceClient;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MSGraphServiceController {

    private final static String TAG = MSGraphServiceController.class.getSimpleName();
    private final IGraphServiceClient graphClient;
    final List<String> events = new ArrayList<>();

    // lorsqu'on crée une nouvelle instance on va chercher une référence à MSGraphServiceClientManager pour l'utiliser
    public MSGraphServiceController(Context context) {
        this.graphClient = MSGraphServiceClientManager.getInstance(context).getGraphServiceClient();
    }

    // obtenir les events
    public SettableFuture<List<String>> getEvents(){
        // SettableFuture = promesse
        final SettableFuture<List<String>> result = SettableFuture.create();
        // création d'une nouvelle demande au client graph
        IEventCollectionRequest request = graphClient.getMe().getEvents().buildRequest(
                // obtenir des champs spécifiques
                Arrays.asList(new Option[]{
                        new QueryOption("$select", "subject,start,end"),
                        new QueryOption("$top", "20"),
                        new QueryOption("$skip", 0)
                })
        );
        Log.d(TAG, "Request object: " + request.toString());

        request.get(new ICallback<IEventCollectionPage>() {
            @Override
            public void success(IEventCollectionPage iEventCollectionPage) {
                // récupère la page actuelle des résultats
                List<Event> listOfEvents = iEventCollectionPage.getCurrentPage();
                for (Event item : listOfEvents){
                    // parcours et ajout des events dans la liste
                    events.add(item.subject);
                }
            }

            @Override
            public void failure(ClientException ex) { ex.printStackTrace(); }
        });
        return result;
    }
}

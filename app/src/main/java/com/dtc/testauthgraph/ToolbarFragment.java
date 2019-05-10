package com.dtc.testauthgraph;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dtc.testauthgraph.auth.AuthenticationController;



/**
 * A simple {@link Fragment} subclass.
 */
public class ToolbarFragment extends Fragment {

    private final static String TAG = ToolbarFragment.class.getSimpleName();

    public ToolbarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toolbar,
                container, false);
        ImageButton logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignout();
            }
        });
        return view;
    }


    // se déconnecter
    private void onSignout() {
        AuthenticationController authenticationController = AuthenticationController.getInstance(getContext());
        authenticationController.signOut();
        Log.d(TAG, " Click onSignout : déco...");
    }

}

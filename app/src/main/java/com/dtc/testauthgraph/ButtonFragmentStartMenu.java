package com.dtc.testauthgraph;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dtc.testauthgraph.screen.MenuApp;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragmentStartMenu extends Fragment {

    private final static String TAG = ButtonFragmentStartMenu.class.getSimpleName();
    Button btnload;

    public ButtonFragmentStartMenu() {
        // Required empty public constructor
    }

//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_button_fragment_start_menu,
//                container, false);
//        Button btnload = (Button) view.findViewById(R.id.btnload);
//        btnload.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v){
//                Log.d(TAG, "onCick fragment ...");
//                // lancer l'activity du menu
//                Intent intent = new Intent(getActivity(),MenuApp.class);
//                ButtonFragmentStartMenu.this.startActivity( intent );
//                getActivity().finish();
//            }
//        });
//        return view;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_button_fragment_start_menu,
                container, false);
        return view;
    }

    private void onClickToButtonStartMenu(View view) {

        Button btnload = (Button) view.findViewById(R.id.btnload);
        btnload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "onCick fragment ...");
                // lancer l'activity du menu
                Intent intent = new Intent(getActivity() , MenuApp.class);
                startActivity( intent );
                getActivity().finish();
            }
        });

    }

}

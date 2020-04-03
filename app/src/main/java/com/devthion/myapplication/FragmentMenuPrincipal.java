package com.devthion.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;


public class FragmentMenuPrincipal extends Fragment {
    FirebaseAuth fAuth;
    TextView pantalla;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fAuth = FirebaseAuth.getInstance();
        pantalla = getView().findViewById(R.id.pantalla);
        pantalla.setText(""+fAuth.toString());
        return inflater.inflate(R.layout.fragment_fragment_menu_principal, container, false);
    }


}

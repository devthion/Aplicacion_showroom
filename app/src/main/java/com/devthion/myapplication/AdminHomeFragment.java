package com.devthion.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class AdminHomeFragment extends Fragment {

    Button btnAgregarLocal;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //HAY QUE AGREGAR ESTO PARA USAR BOTONES Y ESO
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);
        btnAgregarLocal = (Button) view.findViewById(R.id.btnAgregarLocal);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_home, container, false);




    }


}
package com.devthion.myapplication.Administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devthion.myapplication.BuscarCupon;
import com.devthion.myapplication.Perfil;
import com.devthion.myapplication.R;

import androidx.fragment.app.Fragment;


public class QuitarLocalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quitar_local, container, false);
        Intent in = new Intent(getActivity(), BuscarCupon.class);
        startActivity(in);


        return view;
    }


}

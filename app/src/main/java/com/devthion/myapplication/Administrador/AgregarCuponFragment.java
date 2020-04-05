package com.devthion.myapplication.Administrador;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Cupon;


public class AgregarCuponFragment extends Fragment {

    Button btnGuardarCupon;
    TextView etIdCupon, etLocal,etDescuento,etPuntosNecesarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agregar_cupon, container, false);

        btnGuardarCupon = (Button) view.findViewById(R.id.btnGuardarCupon);
        etIdCupon = (TextView) view.findViewById(R.id.etIdCupon);
        etLocal = (TextView) view.findViewById(R.id.etLocal);
        etDescuento = (TextView) view.findViewById(R.id.etDescuento);
        etPuntosNecesarios = (TextView) view.findViewById(R.id.etPuntosNecesarios);

        btnGuardarCupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SE GUARDA EL CUPON EN LA BASE DE DATOS
                Cupon.guardarCupon(etIdCupon.getText().toString(),etLocal.getText().toString(),Integer.parseInt(etDescuento.getText().toString()),Integer.parseInt(etPuntosNecesarios.getText().toString()));
                Toast.makeText(getContext(),"CUPON CREADO CON EXITO",Toast.LENGTH_LONG).show();

                etIdCupon.setText("");
                etDescuento.setText("");
                etLocal.setText("");
                etPuntosNecesarios.setText("");
            }
        });


        return view;
    }
}

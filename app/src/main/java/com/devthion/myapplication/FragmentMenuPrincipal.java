package com.devthion.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.devthion.myapplication.ingreso.InicioSesion;
import com.devthion.myapplication.ingreso.VerificarEmail;
import com.google.firebase.auth.FirebaseAuth;


public class FragmentMenuPrincipal extends Fragment implements View.OnClickListener {

    TextView txtPrueba;
    FirebaseAuth fAuth;
    Button btnCerrarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_menu_principal, container, false);
        btnCerrarSesion = (Button) view.findViewById(R.id.btnCerrarSesionn);
        fAuth = FirebaseAuth.getInstance();
        txtPrueba = (TextView) view.findViewById(R.id.txtPrueba);

        btnCerrarSesion.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCerrarSesionn:
                fAuth.signOut();

                Toast.makeText(getActivity(), "CERRAR SESION", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getActivity(), InicioSesion.class);
                startActivity(in);
                break;

        }
    }
}

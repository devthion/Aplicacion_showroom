package com.devthion.myapplication.Administrador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;



public class AgregarLocalFragment extends Fragment {

    Button btnGuardarLocal;
    TextView etNombre, etCalle, etNumero, etCodPostal, etBarrio, etTipo;
    CheckBox checkHombre, checkMujer,checkNiños;
    List<String> tipos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_agregar_local, container, false);

        btnGuardarLocal = (Button) view.findViewById(R.id.btnGuardarLocal);
        etNombre = (TextView) view.findViewById(R.id.etNombreLocal);
        etCalle = (TextView) view.findViewById(R.id.etCalle);
        etNumero = (TextView) view.findViewById(R.id.etNumero);
        etCodPostal = (TextView) view.findViewById(R.id.etCodPostal);
        etBarrio = (TextView) view.findViewById(R.id.etBarrio);
        etTipo = (TextView) view.findViewById(R.id.etTipo);
        checkHombre = (CheckBox) view.findViewById(R.id.checkRopaHombre);
        checkMujer = (CheckBox) view.findViewById(R.id.checkRopaMujer);
        checkNiños = (CheckBox) view.findViewById(R.id.checkRopaNiños);



        btnGuardarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLocales(tipos);
                Local.guardarLocal(etNombre.getText().toString(),etCalle.getText().toString(),Integer.parseInt(etNumero.getText().toString()),Integer.parseInt(etCodPostal.getText().toString()),etBarrio.getText().toString(),tipos);
                Toast.makeText(getContext(),"LOCAL CREADO CON EXITO",Toast.LENGTH_LONG).show();

                etNombre.setText("");
                etCodPostal.setText("");
                etNumero.setText("");
                etBarrio.setText("");
                etCalle.setText("");
                etTipo.setText("");


            }
        });

        return view;

    }

    public void validarLocales(List pistas){
        String resultado = null;
        if(checkMujer.isChecked()){
             resultado = "Ropa Mujer";
        }
        if(checkHombre.isChecked()){
            resultado = "Ropa Hombre";
        }
        if(checkNiños.isChecked()){
            resultado = "Ropa Niños";
        }
    }

}

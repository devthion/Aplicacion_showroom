package com.devthion.myapplication;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.devthion.myapplication.Interfaces.InterfaceBusquedaUnLocal;
import com.devthion.myapplication.Interfaces.InterfaceRetrieveDataFirebase;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;


public class DetalleLocal extends AppCompatActivity implements View.OnClickListener {

    TextView textV_nombreLocal, textV_descripcionLocal;
    Button btn_dar_calificacion;
    BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalle_local);


        textV_descripcionLocal=findViewById(R.id.textV_descripcionLocal);
        btn_dar_calificacion=findViewById(R.id.btn_dar_calificacion);

        Intent intent = getIntent();
        String idLocal = intent.getStringExtra("idLocal");


        busquedaDeLocalesFirebase.busquedaPorId(idLocal, new InterfaceBusquedaUnLocal() {
            @Override
            public void onCallBack(Local unLocal) {
                textV_descripcionLocal.setText(unLocal.getDescripcion());
            }

        });


        btn_dar_calificacion.setOnClickListener(this);



    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_dar_calificacion:
                alertDialog();
            break;
        }
    }

    //CREAR UNA CLASE DE ALERTDIALOG CON METODO "MOSTRAR"
    public void alertDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)

                .setIcon(android.R.drawable.ic_dialog_alert)

                .setTitle("Are you sure to Exit")

                .setMessage("Exiting will call finish() method")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }



}

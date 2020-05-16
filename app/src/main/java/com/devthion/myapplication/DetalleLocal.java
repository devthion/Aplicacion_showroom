package com.devthion.myapplication;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.devthion.myapplication.Interfaces.InterfaceBusquedaUnLocal;
import com.devthion.myapplication.Interfaces.InterfaceRetrieveDataFirebase;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;


public class DetalleLocal extends AppCompatActivity {

    TextView textV_nombreLocal, textV_descripcionLocal, textV_instagram, textV_barrio, textV_telefono;
    public BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalle_local);

        textV_instagram = findViewById(R.id.textV_instagram);
        textV_descripcionLocal = findViewById(R.id.textV_descripcionLocal);
        textV_nombreLocal=findViewById(R.id.textV_nombreLocal);
        textV_barrio=findViewById(R.id.textV_barrio);
        textV_telefono=findViewById(R.id.textV_telefono);

        Intent intent = getIntent();
        String idLocal = intent.getStringExtra("idLocal");

        busquedaDeLocalesFirebase.busquedaDeUnLocalConId(idLocal, new InterfaceBusquedaUnLocal() {
            @Override
            public void onCallBack(Local unLocal) {
                textV_nombreLocal.setText(unLocal.getNombre());
                textV_descripcionLocal.setText("\""+unLocal.getDescripcion()+"\"");
                textV_instagram.setText("Instagram: " + unLocal.getLinkInstagram());
                textV_barrio.setText("Barrio: "+unLocal.getDireccion().getBarrio());
                textV_telefono.setText("Telefono: "+unLocal.getTelefono());
            }


        });






    }
}

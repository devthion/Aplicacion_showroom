package com.devthion.myapplication;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.devthion.myapplication.modelos.Local;


public class DetalleLocal extends AppCompatActivity {

    TextView textV_nombreLocal, textV_descripcionLocal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalle_local);

        textV_descripcionLocal = findViewById(R.id.textV_descripcionLocal);
        textV_nombreLocal=findViewById(R.id.textV_nombreLocal);


        Intent intent = getIntent();
        String nombreLocal = intent.getStringExtra("nombreLocal");
        String descripcionLocal = intent.getStringExtra("descripcionLocal");

        textV_nombreLocal.setText(nombreLocal);
        textV_descripcionLocal.setText(nombreLocal);



    }
}

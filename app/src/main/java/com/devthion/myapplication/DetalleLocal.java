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


        textV_descripcionLocal=findViewById(R.id.textV_descripcionLocal);

        Intent intent = getIntent();
        String idLocal = intent.getStringExtra("idLocal");


        textV_descripcionLocal.setText(idLocal);




    }
}

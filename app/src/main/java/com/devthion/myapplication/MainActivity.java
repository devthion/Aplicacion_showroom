package com.devthion.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.devthion.myapplication.ingreso.InicioSesion;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBarInicioApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarInicioApp = findViewById(R.id.progressBar_inicio_app);

        //EL HANDLER VA A EJECUTAR EL CODIGO QUE ESTE EN RUN, UNA VEZ TRANSCURRIDO EL TIEMPO EN MILISEGUNDOS INDICADO
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, InicioSesion.class);
                startActivity(intent);
                progressBarInicioApp.setVisibility(View.GONE);
            }
        },2000);



    }






}

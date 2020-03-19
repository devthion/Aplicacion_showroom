package com.devthion.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.devthion.myapplication.ingreso.Registrar;
import com.devthion.myapplication.modelos.ModeloUsuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), Registrar.class));

        Intent intent = new Intent(MainActivity.this, LocacionesEnMaps.class);
        startActivity(intent);

    }






}

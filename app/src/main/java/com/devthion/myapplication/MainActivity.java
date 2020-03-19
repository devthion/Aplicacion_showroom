package com.devthion.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databasePartidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databasePartidas = FirebaseDatabase.getInstance().getReference("usuarios");
        ModeloUsuario unUsuario= new ModeloUsuario("pepe", "asd", 123);
        databasePartidas.child("pepe").setValue(unUsuario);

        Intent intent = new Intent(MainActivity.this, LocacionesEnMaps.class);
        startActivity(intent);

    }






}

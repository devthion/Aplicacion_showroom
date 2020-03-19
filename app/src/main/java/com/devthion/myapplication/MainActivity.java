package com.devthion.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.devthion.myapplication.modelos.ModeloUsuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databasePartidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databasePartidas = FirebaseDatabase.getInstance().getReference("usuarios");
        ModeloUsuario unUsuario= new ModeloUsuario("pepe", "asd", "asd");
        databasePartidas.child("pepe").setValue(unUsuario);


    }






}

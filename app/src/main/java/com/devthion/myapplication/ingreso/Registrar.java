package com.devthion.myapplication.ingreso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devthion.myapplication.R;
import com.google.firebase.database.DatabaseReference;

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText etNombreUsuario, etNuevaContraseña, etNuevoEmail, etContraseñaRep;
    TextView etIniciarSesion;
    DatabaseReference databaseUsuarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        etNombreUsuario = (EditText) findViewById(R.id.etNombreUsuario);
        etNuevaContraseña = (EditText) findViewById(R.id.etNuevaContraseña);
        etNuevoEmail = (EditText) findViewById(R.id.etNuevoEmail);



    }
}

package com.devthion.myapplication.ingreso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devthion.myapplication.R;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText etNombreUsuario, etNuevaContraseña, etNuevoEmail, etContraseñaRep;
    TextView etIniciarSesion;
    FirebaseAuth databaseUsuarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        etNombreUsuario = (EditText) findViewById(R.id.etNombreUsuario);
        etNuevaContraseña = (EditText) findViewById(R.id.etNuevaContraseña);
        etNuevoEmail = (EditText) findViewById(R.id.etNuevoEmail);
        etContraseñaRep =(EditText) findViewById(R.id.etContraseñaRep);

        databaseUsuarios =

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etNuevoEmail.getText().toString().trim();
                String nombre =etNombreUsuario.getText().toString().trim();
                String contraseña = etNuevaContraseña.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    etNuevoEmail.setError("Es necesario que complete el campo Email");
                }
                if(TextUtils.isEmpty(contraseña)){
                    etNuevaContraseña.setError("Es necesario que complete el campo Contraseña");
                }

                //REGISTRAR EL USUARIO EN FIREBASE

                databaseUsuarios
            }
        });



    }
}

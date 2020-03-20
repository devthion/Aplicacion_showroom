package com.devthion.myapplication.ingreso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.MainActivity;
import com.devthion.myapplication.MenuPrincipal;
import com.devthion.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText etNombreUsuario, etNuevaContraseña, etNuevoEmail, etContraseñaRep;
    TextView etIniciarSesion;
    ProgressBar progressBarRegistro;
    FirebaseAuth fAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        etNombreUsuario = (EditText) findViewById(R.id.etNombreUsuario);
        etNuevaContraseña = (EditText) findViewById(R.id.etNuevaContraseña);
        etNuevoEmail = (EditText) findViewById(R.id.etNuevoEmail);
        etContraseñaRep =(EditText) findViewById(R.id.etContraseñaRep);
        etIniciarSesion = (TextView) findViewById(R.id.etIniciarSesion);

        fAuth = FirebaseAuth.getInstance();
        progressBarRegistro = (ProgressBar) findViewById(R.id.progressBarRegistro);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
            finish();
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etNuevoEmail.getText().toString().trim();
                String contraseña = etNuevaContraseña.getText().toString().trim();
                String contraseñaConfirmacion = etContraseñaRep.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    etNuevoEmail.setError("ESTE CAMPO ES NECESARIO");
                    return;
                }
                if(TextUtils.isEmpty(contraseña)){
                    etNuevaContraseña.setError("ESTE CAMPO ES NECESARIO");
                    return;
                }
                if(TextUtils.isEmpty(contraseña)){
                    etContraseñaRep.setError("ESTE CAMPO ES NECESARIO");
                    return;
                }
                if(!etNuevaContraseña.getText().toString().trim().equals(etContraseñaRep.getText().toString().trim())){
                    etContraseñaRep.setError("LA CONTRASEÑA NO COINCIDE");
                    return;
                }

                progressBarRegistro.setVisibility(View.VISIBLE);

                //REGISTRAR EL USUARIO EN FIREBASE

                fAuth.createUserWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Registrar.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
                        }else {
                            Toast.makeText(Registrar.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarRegistro.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });

        etIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),InicioSesion.class));
            }
        });


    }
}

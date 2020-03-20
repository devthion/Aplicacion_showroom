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

import com.devthion.myapplication.MenuPrincipal;
import com.devthion.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    TextView etRegistrarse;
    Button btnIngresar;
    EditText etEmail, etContraseña;
    ProgressBar progressBarInicio;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        fAuth = FirebaseAuth.getInstance();
        progressBarInicio = findViewById(R.id.progressBarInicio);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
            finish();
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = etEmail.getText().toString().trim();
                String contraseña = etContraseña.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    etEmail.setError("ESTE CAMPO ES NECESARIO");
                    return;
                }
                if(TextUtils.isEmpty(contraseña)){
                    etContraseña.setError("ESTE CAMPO ES NECESARIO");
                    return;
                }

                progressBarInicio.setVisibility(View.VISIBLE);

                //VERIFICAR USUARIO

                fAuth.signInWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(InicioSesion.this, "Ha ingresado correcatamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
                        }else {
                            Toast.makeText(InicioSesion.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBarInicio.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });




        etRegistrarse = (TextView) findViewById(R.id.etRegistrarse);
        etRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrar();
            }
        });
    }

    public void openRegistrar(){
        Intent intent = new Intent(this,Registrar.class);
        startActivity(intent);
    }
}

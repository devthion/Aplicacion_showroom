package com.devthion.myapplication.ingreso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.MenuPrincipal;
import com.devthion.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registrar extends AppCompatActivity {

    Button btnRegistrar;
    EditText etNombreUsuario, etContraseña, etEmail, etContraseñaRep;
    TextView etIniciarSesion;
    ProgressBar progressBarRegistro;
    FirebaseAuth fAuth;
    FirebaseFirestore databaseUsuarios;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        etNombreUsuario = (EditText) findViewById(R.id.etNombreUsuario);
        etContraseña = (EditText) findViewById(R.id.etContraseña);
        etEmail = (EditText) findViewById(R.id.etNuevoEmail);
        etContraseñaRep =(EditText) findViewById(R.id.etContraseñaRep);
        etIniciarSesion = (TextView) findViewById(R.id.etIniciarSesion);
        databaseUsuarios = FirebaseFirestore.getInstance();

        fAuth = FirebaseAuth.getInstance();
        progressBarRegistro = (ProgressBar) findViewById(R.id.progressBarRegistro);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
            finish();
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString().trim();
                final String contraseña = etContraseña.getText().toString().trim();
                final String nombre = etNombreUsuario.getText().toString();


                if(TextUtils.isEmpty(email)){
                    etEmail.setError("ESTE CAMPO ES NECESARIO");
                    return;
                }
                if(TextUtils.isEmpty(contraseña)){
                    etContraseña.setError("ESTE CAMPO ES NECESARIO");
                    return;
                }

                if(!etContraseña.getText().toString().trim().equals(etContraseñaRep.getText().toString().trim())){
                    etContraseñaRep.setError("LA CONTRASEÑA NO COINCIDE");
                    return;
                }

                progressBarRegistro.setVisibility(View.VISIBLE);

                //REGISTRAR EL USUARIO EN FIREBASE
                fAuth.createUserWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //ENVIAR LINK DE VERIFICACION
                        FirebaseUser fuser = fAuth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Registrar.this, "Se ha enviado un mail de verificación", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("","Email no enviado : "+e.getMessage());
                            }
                        });

                        if(task.isSuccessful()){
                            Toast.makeText(Registrar.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = databaseUsuarios.collection("Usuarios").document(userID);
                            Map<String,Object> usuario =new HashMap<>();
                            usuario.put("Nombre",nombre);
                            usuario.put("Email",email);
                            usuario.put("Contraseña",contraseña);

                            documentReference.set(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("USUARIO AÑADIDO","Se ha creado un perfil para el usuario: "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("USUARIO NO AÑADIDO","onFailure: "+e.toString());
                                }
                            });


                            startActivity(new Intent(getApplicationContext(), VerificarEmail.class));
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
                mostrarInicioSesion();
            }
        });


    }

    public void mostrarInicioSesion(){
        startActivity(new Intent(getApplicationContext(),InicioSesion.class));
    }
}

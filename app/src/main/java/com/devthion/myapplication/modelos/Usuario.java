package com.devthion.myapplication.modelos;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devthion.myapplication.ingreso.Registrar;
import com.devthion.myapplication.ingreso.VerificarEmail;
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

import androidx.annotation.NonNull;

import static androidx.core.content.ContextCompat.startActivity;


public class Usuario {

    private String nombre;
    private String email;
    private String contraseña;

    FirebaseAuth fAuth;
    FirebaseFirestore databaseUsuarios;
    String userID;


    public Usuario(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        //almacenarEnFirebase(email, nombre, contraseña);
    }

    /*private void almacenarEnFirebase(final String email, final String nombre, final String contraseña) {
        databaseUsuarios = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

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
                    //Toast.makeText(Registrar.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
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


                    //startActivity(new Intent(getApplicationContext(), VerificarEmail.class));
                }else {
                    //Registar.mostrarInicioSesion();
                    //Toast.makeText(Registrar.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    //Registrar.progressBarRegistro.setVisibility(View.GONE);
                }
            }
        });
    }*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}

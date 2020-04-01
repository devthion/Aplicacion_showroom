package com.devthion.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Perfil extends AppCompatActivity {

    FirebaseAuth fAuth;
    String userID,userNombre;
    TextView txtId,etPerfilNombre;
    //DECLARAMOS LA BASE DE DATOS DE LOS USUARIOS
    FirebaseFirestore databaseUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfil);
        txtId =(TextView) findViewById(R.id.txtPerfilId);
        etPerfilNombre =(TextView) findViewById(R.id.etPerfilNombre);
        databaseUsuarios = FirebaseFirestore.getInstance();

        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = databaseUsuarios.collection("Usuarios").document(userID);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    userNombre = (String) documentSnapshot.getData().get("Nombre");
                    etPerfilNombre.setText("NOMBRE DEL USUARIO " + userNombre);
                }
            }
        });




        txtId.setText("ID "+userID);


    }
}

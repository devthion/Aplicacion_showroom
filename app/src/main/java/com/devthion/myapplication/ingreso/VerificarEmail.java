package com.devthion.myapplication.ingreso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.MenuPrincipal;
import com.devthion.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerificarEmail extends AppCompatActivity {

    Button btnEnviarLink,btnYaVerifique;
    TextView etVerificarMail;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_verificar_email);

        btnEnviarLink = (Button) findViewById(R.id.btnVerificarMail);
        etVerificarMail =(TextView) findViewById(R.id.etVerificarMail);
        btnYaVerifique = (Button) findViewById(R.id.btnYaVerifique);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();
        final FirebaseUser user = fAuth.getCurrentUser();

        //PREGUNTA SI EL EMAIL HA SIDO VERIFICADO
        if(!user.isEmailVerified()){
            btnEnviarLink.setVisibility(View.VISIBLE);
            etVerificarMail.setVisibility(View.VISIBLE);

            btnEnviarLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //MANDA EL MAIL NUEVAMENTE PARA QUE SE VERIFIQUE
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(VerificarEmail.this, "Se ha enviado un mail de verificacion", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("tag","Email no enviado : "+e.getMessage());
                        }
                    });
                }
            });
        }else {
            startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
        }

        //TE MANDA AL INICIO DE SESION PARA QUE VERIFIQUES
        btnYaVerifique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), InicioSesion.class));
                finish();
            }
        });

    }
}

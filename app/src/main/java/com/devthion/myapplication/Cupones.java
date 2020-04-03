package com.devthion.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Cupones extends AppCompatActivity {
    DatabaseReference databaseCupones;
    FirebaseAuth fAuth;
    String prueba = "";
    TextView pantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cupones);

        pantalla = findViewById(R.id.pantalla);

        fAuth = FirebaseAuth.getInstance();

        //Referencio la tabla "usuarios"
        databaseCupones= FirebaseDatabase.getInstance().getReference("usuarios");


        //el add value event listener, se va a ejecutar cada vez que haya un ingreso de valor en la tabla
        //o sea si se actualiza un valor, se va a ejecutar el codigo
        databaseCupones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //for para recorrer dentro de usuarios, todos los usuario y asi acceder a sus cupones
                for (DataSnapshot unUsuario : dataSnapshot.getChildren()) {
                        //for para recorrer los cupones de unUsuario
                        for(DataSnapshot unCupon: unUsuario.child("cupones").getChildren() ){
                            prueba = prueba +unCupon.getValue().toString()+"\n";
                            pantalla.setText(prueba);
                        }


                }

            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

}

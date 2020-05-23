package com.devthion.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;
import com.devthion.myapplication.Interfaces.InterfaceObtenerPromedioEstrellas;
import com.devthion.myapplication.modelos.Local;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CalificacionesFirebase extends AppCompatActivity {
    DatabaseReference databaseCalificaciones= FirebaseDatabase.getInstance().getReference("Calificaciones");



    public void obtenerPromedioEstrellas(final String localId, final InterfaceObtenerPromedioEstrellas interfaceObtenerPromedioEstrellas){
        databaseCalificaciones.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float promedioPuntuacion = 0;
                int calificacionesTotales = 0;
                for (DataSnapshot unaCalificacion : dataSnapshot.getChildren()){
                    if(true){
                        promedioPuntuacion += Float.parseFloat(unaCalificacion.child("estrellas").getValue().toString());
                        calificacionesTotales++;


                    }

                }
                promedioPuntuacion = promedioPuntuacion/calificacionesTotales;
                interfaceObtenerPromedioEstrellas.onCallBack(promedioPuntuacion);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

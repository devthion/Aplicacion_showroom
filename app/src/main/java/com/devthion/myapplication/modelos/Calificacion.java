package com.devthion.myapplication.modelos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Calificacion {
    private String calificacion_id;
    private String usuario_id;
    private String calificacion;
    private String local_id;
    private float estrellas;

    DatabaseReference databaseCalificaciones = FirebaseDatabase.getInstance().getReference().child("Calificaciones");

    public Calificacion(String usuario_id, String calificacion, String local_id, float estrellas) {
        this.usuario_id = usuario_id;
        this.calificacion = calificacion;
        this.local_id = local_id;
        this.estrellas=estrellas;
    }

    public void almacenarCalificacion(){
        Map<String,Object> unaCalificacion =new HashMap<>();
        unaCalificacion.put("usuario_id", usuario_id);
        unaCalificacion.put("calificacion", calificacion);
        unaCalificacion.put("local_id", local_id);
        unaCalificacion.put("estrellas", estrellas);
        calificacion_id=databaseCalificaciones.push().getKey();
        databaseCalificaciones.child(calificacion_id).setValue(unaCalificacion);

    }
}

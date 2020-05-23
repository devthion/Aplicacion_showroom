package com.devthion.myapplication.modelos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Calificacion {
    private String calificacion_id;
    private String usuario_email;
    private String calificacion;
    private String local_id;

    DatabaseReference databaseCalificaciones = FirebaseDatabase.getInstance().getReference().child("Calificaciones");

    public Calificacion(String usuario_email, String calificacion, String local_id) {
        this.usuario_email = usuario_email;
        this.calificacion = calificacion;
        this.local_id = local_id;
    }

    public void almacenarCalificacion(){
        Map<String,Object> unaCalificacion =new HashMap<>();
        unaCalificacion.put("usuario_email", usuario_email);
        unaCalificacion.put("calificacion", calificacion);
        unaCalificacion.put("local_id", local_id);
        calificacion_id=databaseCalificaciones.push().getKey();
        databaseCalificaciones.child(calificacion_id).setValue(unaCalificacion);

    }
}

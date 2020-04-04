package com.devthion.myapplication.modelos;


import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class Cupon {

    protected String idCupon;
    protected String idLocal;
    protected int descuento;
    protected int puntosNecesarios;


    static DatabaseReference databaseCupones;

    public Cupon(String idCupon, String idLocal, int descuento, int puntosNecesarios) {
        this.idCupon = idCupon;
        this.idLocal = idLocal;
        this.descuento = descuento;
        this.puntosNecesarios = puntosNecesarios;
    }

    public static void guardarCupon(String idCupon, String idLocal, int descuento, int puntosNecesarios){
        databaseCupones = FirebaseDatabase.getInstance().getReference().child("Cupones");
        String maxId = databaseCupones.push().getKey();

        Map<String,Object> cupon =new HashMap<>();
        cupon.put("Codigo",idCupon);
        cupon.put("Local",idLocal);
        cupon.put("Descuento",descuento);
        cupon.put("Puntos Necesarios",puntosNecesarios);
        databaseCupones.child(String.valueOf(maxId)).setValue(cupon);
    }

    public String getIdCupon() {
        return idCupon;
    }

    public void setIdCupon(String idCupon) {
        this.idCupon = idCupon;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getPuntosNecesarios() {
        return puntosNecesarios;
    }

    public void setPuntosNecesarios(int puntosNecesarios) {
        this.puntosNecesarios = puntosNecesarios;
    }
}

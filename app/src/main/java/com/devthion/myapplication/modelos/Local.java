package com.devthion.myapplication.modelos;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class Local {

    String nombre;
    String calle;
    int numero;
    int codPostal;
    String barrio;
    String tipo;

    static DatabaseReference databaseCupones = FirebaseDatabase.getInstance().getReference().child("Locales");
    static long maxId;

    public Local(String nombre, String calle, int numero, int codPostal, String barrio, String tipo) {
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
        this.barrio = barrio;
        this.tipo = tipo;
    }

    public static void guardarLocal(String nombre, String calle, int numero, int codPostal, String barrio, String tipo){
        databaseCupones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxId= dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Map<String,Object> local =new HashMap<>();
        local.put("Nombre",nombre);
        local.put("Calle",calle);
        local.put("Numero",numero);
        local.put("Codigo Postal",codPostal);
        local.put("Barrio",barrio);
        local.put("Tipo",tipo);
        databaseCupones.child(String.valueOf(maxId+1)).setValue(local);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

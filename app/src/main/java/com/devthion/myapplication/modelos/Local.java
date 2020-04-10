package com.devthion.myapplication.modelos;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

public class Local {

    protected static String nombre;
    protected static EstructuraLocal direccion;
    protected static List<String> categorias;
    protected static String Descripcion;
    protected static int telefono;


    static DatabaseReference databaseLocales = FirebaseDatabase.getInstance().getReference().child("Locales");

    public Local(String nombre, String calle, int numero, int codPostal, String barrio, List<String> tipos) {
        this.nombre = nombre;
        this.calle = ;
        this.numero = numero;
        this.codPostal = codPostal;
        this.barrio = barrio;
        this.tipos = tipos;
    }

    public static void guardarLocal(){
        String maxId = databaseLocales.push().getKey();

        Map<String,Object> local =new HashMap<>();
        local.put("Nombre",nombre);
        local.put("Calle",calle);
        local.put("Numero",numero);
        local.put("Codigo Postal",codPostal);
        local.put("Barrio",barrio);
        local.put("Tipo",tipos);
        databaseLocales.child(String.valueOf(maxId)).setValue(local);
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

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

    protected String nombre;
    protected EstructuraLocal direccion;
    protected List<String> categorias;
    protected String Descripcion;
    protected int telefono;

    static DatabaseReference databaseLocales = FirebaseDatabase.getInstance().getReference().child("Locales");

    public Local(String nombre, EstructuraLocal direccion, List<String> categorias, String descripcion, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.categorias = categorias;
        Descripcion = descripcion;
        this.telefono = telefono;
    }

    public void guardarLocal(){
        String maxId = databaseLocales.push().getKey();

        Map<String,Object> local =new HashMap<>();
        local.put("Nombre",nombre);
        local.put("Calle",direccion.getCalle());
        local.put("Numero",direccion.getNumero());
        local.put("Codigo Postal",direccion.getCodigoPostal());
        local.put("Barrio",direccion.getBarrio());
        local.put("Categorias",categorias);
        local.put("Descripcion",Descripcion);
        local.put("telefono",telefono);
        databaseLocales.child(String.valueOf(maxId)).setValue(local);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstructuraLocal getDireccion() {
        return direccion;
    }

    public void setDireccion(EstructuraLocal direccion) {
        this.direccion = direccion;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public static DatabaseReference getDatabaseLocales() {
        return databaseLocales;
    }

    public static void setDatabaseLocales(DatabaseReference databaseLocales) {
        Local.databaseLocales = databaseLocales;
    }
}

package com.devthion.myapplication.modelos;

import com.devthion.myapplication.modelos.TiposEstructuras.EstructuraLocal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Local {

    private String idLocal;
    private String nombre;
    private EstructuraLocal direccion;
    private List<String> categorias;
    private String descripcion;
    private int telefono;
    private String linkInstagram;
    private String linkPaginaWeb;

    DatabaseReference databaseLocales = FirebaseDatabase.getInstance().getReference().child("Locales");


    public Local(String idLocal,String nombre, EstructuraLocal direccion, List<String> categorias, String descripcion, int telefono, String linkInstagram, String linkPaginaWeb) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.categorias = categorias;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.linkInstagram = linkInstagram;
        this.linkPaginaWeb = linkPaginaWeb;
    }
    
    public void almacenarLocal(){

        Map<String,Object> local =new HashMap<>();
        local = direccion.almacenarLocal(idLocal,nombre,categorias,descripcion,telefono,linkInstagram,linkPaginaWeb);

        databaseLocales.child(String.valueOf(idLocal)).setValue(local);
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
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
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public String getLinkPaginaWeb() {
        return linkPaginaWeb;
    }

    public void setLinkPaginaWeb(String linkPaginaWeb) {
        this.linkPaginaWeb = linkPaginaWeb;
    }
}

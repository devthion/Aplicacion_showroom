package com.devthion.myapplication.modelos.TiposEstructuras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalACalle extends EstructuraLocal {

    protected int numeroLocal;

    public LocalACalle(int numeroLocal) {
        this.tipoLocal = "Departamento";
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.numeroLocal = numeroLocal;
        this.barrio = barrio;
        this.codigoPostal = codigoPostal;

    }

    public int getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(int numeroLocal) {
        this.numeroLocal = numeroLocal;
    }


    @Override
    public Map<String, Object> almacenarLocal(String idLocal, String nombre, List<String> categorias, String descripcion, int telefono, String linkInstagram, String linkPaginaWeb) {
        Map<String,Object> local =new HashMap<>();

        local.put("Nombre",nombre);
        local.put("Tipo Local",tipoLocal);
        local.put("Calle",calle);
        local.put("Numero",numeroCalle);
        local.put("Numero Local",numeroLocal);
        local.put("Codigo Postal",codigoPostal);
        local.put("Barrio",barrio);
        local.put("Categorias",categorias);
        local.put("Descripcion",descripcion);
        local.put("telefono",telefono);

        return local;
    }
}

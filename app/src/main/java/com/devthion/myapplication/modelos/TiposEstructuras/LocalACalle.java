package com.devthion.myapplication.modelos.TiposEstructuras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalACalle extends EstructuraLocal {

    protected int numeroLocal;

    public LocalACalle(String calle,int numeroCalle,int numeroLocal,String barrio,int codigoPostal) {
        super("Departamento",calle,numeroCalle,barrio,codigoPostal);
        /*this.tipoLocal = "Local A Calle";
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.barrio = barrio;
        this.codigoPostal = codigoPostal;*/
        this.numeroLocal = numeroLocal;

    }

    public int getNumeroLocal() {
        return numeroLocal;
    }


    @Override
    public Map<String, Object> almacenarLocal(String idLocal, String nombre, List<String> categorias, String descripcion, int telefono, String linkInstagram, String linkPaginaWeb, boolean envio) {
        Map<String,Object> local =new HashMap<>();

        local.put("idLocal", idLocal);
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
        local.put("Instagram",linkInstagram);
        local.put("Sitio Web",linkPaginaWeb);
        local.put("Hace envios",envio);

        return local;
    }
}

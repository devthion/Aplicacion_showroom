package com.devthion.myapplication.modelos.TiposEstructuras;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Departamento extends EstructuraLocal {

    protected int piso;
    protected int departamento;

    public Departamento(String calle,int numeroCalle,int piso, int departamento,String barrio,int codigoPostal) {
        this.tipoLocal = "Departamento";
        this.calle = calle;
        this.numeroCalle = numeroCalle;
        this.piso = piso;
        this.departamento = departamento;
        this.barrio = barrio;
        this.codigoPostal = codigoPostal;
    }


    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }


    @Override
    public Map<String, Object> almacenarLocal(String idLocal, String nombre, List<String> categorias, String descripcion, int telefono, String linkInstagram, String linkPaginaWeb, boolean envio) {

        Map<String,Object> local =new HashMap<>();

        local.put("idLocal", idLocal);
        local.put("Nombre",nombre);
        local.put("Tipo Local",tipoLocal);
        local.put("Calle",calle);
        local.put("Numero",numeroCalle);
        local.put("Piso",piso);
        local.put("Departamento",departamento);
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


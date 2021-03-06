package com.devthion.myapplication.modelos.TiposEstructuras;

import java.util.List;
import java.util.Map;

public class EstructuraLocal {


    protected String tipoLocal;
    protected String calle;
    protected int numeroCalle;
    protected String barrio;
    protected int codigoPostal;

    public EstructuraLocal( String tipoLocal, String calle, int numeroCalle, String barrio, int codigoPostal){
        this.tipoLocal=tipoLocal;
        this.calle=calle;
        this.numeroCalle=numeroCalle;
        this.barrio=barrio;
        this.codigoPostal=codigoPostal;
    }


    public String getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(String tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numeroCalle;
    }

    public void setNumero(int numero) {
        this.numeroCalle = numero;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Map<String, Object> almacenarLocal(String idLocal, String nombre, List<String> categorias, String descripcion, int telefono, String linkInstagram, String linkPaginaWeb, boolean envio) {
        return null;
    }
}

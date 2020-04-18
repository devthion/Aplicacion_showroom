package com.devthion.myapplication.BuscarLocal;

import com.devthion.myapplication.modelos.TiposEstructuras.EstructuraLocal;

public class CadenaPorLocal {

    private String idLocal;
    private String nombreLocal;
    private EstructuraLocal direccion;
    private String cadenaBusqueda;

    public CadenaPorLocal(String idLocal, String nombreLocal, EstructuraLocal direccion, String cadenaBusqueda) {
        this.idLocal = idLocal;
        this.nombreLocal = nombreLocal;
        this.direccion = direccion;
        this.cadenaBusqueda = cadenaBusqueda;
    }

    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public EstructuraLocal getDireccion() {
        return direccion;
    }

    public void setDireccion(EstructuraLocal direccion) {
        this.direccion = direccion;
    }

    public String getCadenaBusqueda() {
        return cadenaBusqueda;
    }

    public void setCadenaBusqueda(String cadenaBusqueda) {
        this.cadenaBusqueda = cadenaBusqueda;
    }
}

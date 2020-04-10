package com.devthion.myapplication.modelos.TiposEstructuras;

import com.devthion.myapplication.modelos.EstructuraLocal;

public class LocalACalle extends EstructuraLocal {

    protected int numeroLocal;

    public LocalACalle(int numeroLocal) {
        this.numeroLocal = numeroLocal;
    }

    public int getNumeroLocal() {
        return numeroLocal;
    }

    public void setNumeroLocal(int numeroLocal) {
        this.numeroLocal = numeroLocal;
    }
}

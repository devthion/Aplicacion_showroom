package com.devthion.myapplication.modelos.TiposEstructuras;

import com.devthion.myapplication.modelos.EstructuraLocal;

public class Departamento extends EstructuraLocal {

    protected int piso;
    protected int departamento;

    public Departamento(int piso, int departamento) {
        this.piso = piso;
        this.departamento = departamento;
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
}

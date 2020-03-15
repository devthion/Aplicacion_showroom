package com.devthion.myapplication;

public class ModeloUsuario {

    String nombre;
    String apellido;
    int dni;


    public ModeloUsuario(String nombre, String apellido, int dni){
        this.apellido= apellido;
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}

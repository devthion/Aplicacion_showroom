package com.devthion.myapplication.modelos;

public class UploadFotoPerfil {
    private String nombre;
    private String imageUrl;

    public UploadFotoPerfil(){

    }
    public UploadFotoPerfil(String nombre, String imageUrl){
        this.nombre = nombre;
        this.imageUrl = imageUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

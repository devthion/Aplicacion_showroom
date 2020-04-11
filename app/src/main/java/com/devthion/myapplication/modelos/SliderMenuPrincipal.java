package com.devthion.myapplication.modelos;

public class SliderMenuPrincipal {
    private int image;
    private String title;
    private String descripcion;

    public SliderMenuPrincipal(int image, String title, String descripcion) {
        this.image = image;
        this.title = title;
        this.descripcion = descripcion;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

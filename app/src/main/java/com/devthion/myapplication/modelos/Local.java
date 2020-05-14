package com.devthion.myapplication.modelos;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.devthion.myapplication.modelos.TiposEstructuras.EstructuraLocal;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Local {

    private String idLocal;
    private String nombre;
    private EstructuraLocal direccion;
    private List<String> categorias;
    private String descripcion;
    private int telefono;
    private String linkInstagram;
    private String linkPaginaWeb;
    private Context context;
    private boolean envio;

    DatabaseReference databaseLocales = FirebaseDatabase.getInstance().getReference().child("Locales");


    public Local(String idLocal, String nombre, EstructuraLocal direccion, List<String> categorias, String descripcion, int telefono, String linkInstagram, String linkPaginaWeb, boolean envio) {
        this.idLocal = idLocal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.categorias = categorias;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.linkInstagram = linkInstagram;
        this.linkPaginaWeb = linkPaginaWeb;
        this.envio = envio;
    }
    public void setContext(Context context){
        this.context=context;
    }
    public void almacenarLocal(){
        Map<String,Object> local =new HashMap<>();
        local = direccion.almacenarLocal(idLocal,nombre,categorias,descripcion,telefono,linkInstagram,linkPaginaWeb);
        String cadenaBusqueda = obtenerCadena(nombre,direccion,categorias);
        LatLng coordenadas = obtenerCoordenadasDeUnaDireccion(direccion.getCodigoPostal(),direccion.getCalle(),direccion.getNumero());


        databaseLocales.child(String.valueOf(idLocal)).setValue(local);
        databaseLocales.child(String.valueOf(idLocal)).child("Cadena de Busqueda").setValue(cadenaBusqueda);
        databaseLocales.child(String.valueOf(idLocal)).child("Coordenadas").child("Latitud").setValue(coordenadas.latitude);
        databaseLocales.child(String.valueOf(idLocal)).child("Coordenadas").child("Longitud").setValue(coordenadas.longitude);
    }

    private LatLng obtenerCoordenadasDeUnaDireccion(int codigoPostal, String calle, int numero){
        Geocoder coder = new Geocoder(context);
        List<Address> addresses;
        Double latitud = Double.valueOf(0), longitud= Double.valueOf(0);

        try {
            String direccion= calle + numero;
            Address location = null;
            addresses=coder.getFromLocationName(direccion,10);
            for(Address address:addresses){

                if(address.getPostalCode().contains(String.valueOf(codigoPostal))){
                    location=address;
                    latitud=location.getLatitude();
                    longitud=location.getLongitude();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LatLng(latitud,longitud);
    }

    private String obtenerCadena(String nombre, EstructuraLocal direccion, List<String> categorias) {
        String cadena = null;

        cadena = nombre;
        for(String categoria : categorias){
            cadena += " "+categoria;
        }
        cadena += direccion.getCalle() + " " + direccion.getNumero() +" " + direccion.getBarrio();

        return cadena;
    }


    public String getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstructuraLocal getDireccion() {
        return direccion;
    }

    public void setDireccion(EstructuraLocal direccion) {
        this.direccion = direccion;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getLinkInstagram() {
        return linkInstagram;
    }

    public void setLinkInstagram(String linkInstagram) {
        this.linkInstagram = linkInstagram;
    }

    public String getLinkPaginaWeb() {
        return linkPaginaWeb;
    }

    public void setLinkPaginaWeb(String linkPaginaWeb) {
        this.linkPaginaWeb = linkPaginaWeb;
    }
}

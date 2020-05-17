package com.devthion.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;
import com.devthion.myapplication.Interfaces.InterfaceBusquedaLocal;
import com.devthion.myapplication.Interfaces.InterfaceObtencionListaMarkersYTitulos;
import com.devthion.myapplication.Interfaces.InterfaceRetrieveDataFirebase;
import com.devthion.myapplication.modelos.Local;
import com.devthion.myapplication.modelos.TiposEstructuras.Departamento;
import com.devthion.myapplication.modelos.TiposEstructuras.EstructuraLocal;
import com.devthion.myapplication.modelos.TiposEstructuras.LocalACalle;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BusquedaDeLocalesFirebase extends AppCompatActivity {



    DatabaseReference databaseLocales= FirebaseDatabase.getInstance().getReference("Locales");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        databaseLocales= FirebaseDatabase.getInstance().getReference("Locales");

    }

    public void busquedaPorCadena(final InterfaceBusquedaLocal interfaceBusquedaLocal){

        databaseLocales.addValueEventListener(new ValueEventListener() {
            CadenaPorLocal localCadena;
            List<CadenaPorLocal> localesCadenas = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot unLocal : dataSnapshot.getChildren()){

                    localCadena = new CadenaPorLocal(unLocal.child("idLocal").getValue().toString(),
                            unLocal.child("Nombre").getValue().toString(),generarEstructura(unLocal),
                            unLocal.child("Cadena de Busqueda").getValue().toString());

                    localesCadenas.add(localCadena);
                    interfaceBusquedaLocal.onCallBack(localesCadenas);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void busquedaLocales(final InterfaceRetrieveDataFirebase interfaceRetrieveDataFirebase){

        databaseLocales.addValueEventListener(new ValueEventListener() {
            Local local;
            final ArrayList<Local> locales = new ArrayList<>();

            final List<String> categorias = new ArrayList<String>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot unLocal : dataSnapshot.getChildren()){

                    for(DataSnapshot unaCategoria: unLocal.child("Categorias").getChildren() ){
                        categorias.add(unaCategoria.getValue().toString());
                    }

                        local = new Local(unLocal.child("idLocal").getValue().toString(),
                                unLocal.child("Nombre").getValue().toString(),
                                generarEstructura(unLocal), categorias,
                                unLocal.child("Descripcion").getValue().toString(),
                                Integer.parseInt(unLocal.child("telefono").getValue().toString()),
                                unLocal.child("Instagram").getValue().toString(),
                                unLocal.child("Sitio Web").getValue().toString(),
                                Boolean.parseBoolean(unLocal.child("Hace envios").getValue().toString()) );
                        locales.add(local);
                    interfaceRetrieveDataFirebase.onCallBack(locales);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void busquedaPorCategoria(final String categoria, final InterfaceRetrieveDataFirebase interfaceRetrieveDataFirebase){


        databaseLocales.addValueEventListener(new ValueEventListener() {
            Local local;
            ArrayList<Local> locals=new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot unLocal : dataSnapshot.getChildren()) {

                    for(DataSnapshot unaCategoria: unLocal.child("Categorias").getChildren() ){

                        if(unaCategoria.getValue().equals(categoria)){
                            List<String> categorias = new ArrayList<String>();
                            categorias.add(unaCategoria.getValue().toString());
                            local= new Local(unLocal.child("idLocal").getValue().toString(),
                                    unLocal.child("Nombre").getValue().toString(), generarEstructura(unLocal),
                                    categorias, unLocal.child("Descripcion").getValue().toString(),
                                    Integer.parseInt(unLocal.child("telefono").getValue().toString()),
                                    unLocal.child("Instagram").getValue().toString(),
                                    unLocal.child("Sitio Web").getValue().toString(),
                                    Boolean.parseBoolean(unLocal.child("Hace envios").getValue().toString()));
                            locals.add(local);
                            interfaceRetrieveDataFirebase.onCallBack(locals);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    public void busquedaMarkersYTitulosDeLocales(final InterfaceObtencionListaMarkersYTitulos interfaceObtencionListaMarkersYTitulos){

        databaseLocales.addValueEventListener(new ValueEventListener() {
            List<LatLng> listMarkers = new ArrayList<>();
            List<String> listTitulosMarkers = new ArrayList<>();
            List<String> listId = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot unLocal : dataSnapshot.getChildren()){
                    double latitud = (double) unLocal.child("Coordenadas").child("Latitud").getValue();
                    double longitud = (double) unLocal.child("Coordenadas").child("Longitud").getValue();
                    String nombreUnLocal = unLocal.child("Nombre").getValue().toString();
                    LatLng coordenadas = new LatLng(latitud, longitud);
                    listMarkers.add(coordenadas);
                    listTitulosMarkers.add(nombreUnLocal);
                    listId.add(unLocal.child("idLocal").getValue().toString());
                    interfaceObtencionListaMarkersYTitulos.onCallBack(listMarkers,listTitulosMarkers,listId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public EstructuraLocal generarEstructura(DataSnapshot unLocal){
        EstructuraLocal estructuraLocal;
        if(unLocal.child("Tipo Local").getValue().equals("Departamento")){
            estructuraLocal = new Departamento(unLocal.child("Calle").getValue().toString(),Integer.parseInt(unLocal.child("Numero").getValue().toString()),Integer.parseInt(unLocal.child("Piso").getValue().toString()),Integer.parseInt(unLocal.child("Departamento").getValue().toString()),unLocal.child("Barrio").getValue().toString(),Integer.parseInt(unLocal.child("Codigo Postal").getValue().toString()));
        }else{
            estructuraLocal=new LocalACalle(unLocal.child("Calle").getValue().toString(),Integer.parseInt(unLocal.child("Numero").getValue().toString()),Integer.parseInt(unLocal.child("numeroLocal").getValue().toString()),unLocal.child("Barrio").getValue().toString(),Integer.parseInt(unLocal.child("Codigo Postal").getValue().toString()));
        }
        return estructuraLocal;
    }

}

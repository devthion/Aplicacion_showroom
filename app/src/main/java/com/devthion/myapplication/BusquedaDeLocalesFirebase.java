package com.devthion.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.devthion.myapplication.modelos.Local;
import com.devthion.myapplication.modelos.TiposEstructuras.Departamento;
import com.devthion.myapplication.modelos.TiposEstructuras.EstructuraLocal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BusquedaDeLocalesFirebase extends AppCompatActivity {
    boolean flag;


    DatabaseReference databaseLocales= FirebaseDatabase.getInstance().getReference("Locales");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        databaseLocales= FirebaseDatabase.getInstance().getReference("Locales");

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
                            EstructuraLocal estructuraLocal = null;
                            List<String> categorias = new ArrayList<String>();
                            categorias.add("asd");
                            if(unLocal.child("Tipo Local").equals("Departamento")){
                                estructuraLocal = new Departamento(unLocal.child("Calle").getValue().toString(),Integer.parseInt(unLocal.child("Numero").getValue().toString()),Integer.parseInt(unLocal.child("Piso").getValue().toString()),Integer.parseInt(unLocal.child("Departamento").getValue().toString()),unLocal.child("Barrio").getValue().toString(),Integer.parseInt(unLocal.child("Codigo Postal").getValue().toString()));
                            }else{
                                //----
                            }
                            local= new Local(unLocal.child("Nombre").getValue().toString(), estructuraLocal, categorias, unLocal.child("Descripcion").getValue().toString(), Integer.parseInt(unLocal.child("telefono").getValue().toString()), unLocal.child("Instagram").getValue().toString(), unLocal.child("Sitio Web").getValue().toString());
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

}

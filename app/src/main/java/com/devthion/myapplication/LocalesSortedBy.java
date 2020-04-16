package com.devthion.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.modelos.Local;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalesSortedBy extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdaptadorRecyclerViewLocalesSortedBy adaptadorRecyclerViewLocalesSortedBy;
    BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();
    List<Local> listaDeLocales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_locales_sorted_by);


        recyclerView = findViewById(R.id.recyclerV_lista_locales_sorted_by);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //---------------
        //obtengo la lista de locales para mostrar
       Intent intent = getIntent();
       String condicion = intent.getStringExtra("condicion");
       if(condicion.equals("categoria")){
           String categoria = intent.getStringExtra("variable");
           busquedaDeLocalesFirebase.busquedaPorCategoria(categoria, new InterfaceRetrieveDataFirebase() {
               @Override
               public void onCallBack(ArrayList<Local> locales) {
                   setAdapterEnRecycler(locales);
               }
           });
       }

        //--------------


    }
    public void setAdapterEnRecycler(List<Local> locales){
        adaptadorRecyclerViewLocalesSortedBy = new AdaptadorRecyclerViewLocalesSortedBy(locales);
        recyclerView.setAdapter(adaptadorRecyclerViewLocalesSortedBy);
    }
}

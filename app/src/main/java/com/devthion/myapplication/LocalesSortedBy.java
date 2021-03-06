package com.devthion.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.Interfaces.InterfaceRetrieveDataFirebase;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
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
                   listaDeLocales=locales;
                   setAdapterEnRecycler(locales);
               }
           });
       }

        //--------------


    }
    public void setAdapterEnRecycler(final List<Local> locales){
        adaptadorRecyclerViewLocalesSortedBy = new AdaptadorRecyclerViewLocalesSortedBy(locales);
        adaptadorRecyclerViewLocalesSortedBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String idLocal=locales.get(recyclerView.getChildAdapterPosition(v)).getIdLocal();


                Intent intent = new Intent(getApplicationContext(), DetalleLocal.class);

                intent.putExtra("idLocal",idLocal);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adaptadorRecyclerViewLocalesSortedBy);
    }


}

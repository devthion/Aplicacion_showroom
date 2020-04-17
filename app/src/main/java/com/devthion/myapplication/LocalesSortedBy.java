package com.devthion.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.modelos.Local;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.gson.Gson;

import java.io.Serializable;
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
                String nombreLocal= locales.get(recyclerView.getChildAdapterPosition(v)).getNombre();
                String descripcionLocal= locales.get(recyclerView.getChildAdapterPosition(v)).getDescripcion();
                String sitioWebLocal= locales.get(recyclerView.getChildAdapterPosition(v)).getLinkPaginaWeb();
                String instagramLocal= locales.get(recyclerView.getChildAdapterPosition(v)).getLinkInstagram();
                String telefonoLocal= String.valueOf(locales.get(recyclerView.getChildAdapterPosition(v)).getTelefono());

                Intent intent = new Intent(getApplicationContext(), DetalleLocal.class);

                intent.putExtra("idLocal",idLocal);
                intent.putExtra("nombreLocal",nombreLocal);
                intent.putExtra("descripcionLocal",descripcionLocal);
                intent.putExtra("sitioWebLocal",sitioWebLocal);
                intent.putExtra("instagramLocal",instagramLocal);
                intent.putExtra("telefonoLocal",telefonoLocal);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adaptadorRecyclerViewLocalesSortedBy);
    }


}

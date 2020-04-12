package com.devthion.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalesSortedBy extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdaptadorRecyclerViewLocalesSortedBy adaptadorRecyclerViewLocalesSortedBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_locales_sorted_by);

        recyclerView = findViewById(R.id.recyclerV_lista_locales_sorted_by);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adaptadorRecyclerViewLocalesSortedBy = new AdaptadorRecyclerViewLocalesSortedBy(obtenerLocales());
        recyclerView.setAdapter(adaptadorRecyclerViewLocalesSortedBy);
    }
    public List<Local> obtenerLocales(){
        List<Local> locals = new ArrayList<>();
        locals.add(new Local("Nombre de local 1", null, null, "Descripcion del local 1", 1));
        locals.add(new Local("Nombre de local 2", null, null, "Descripcion del local 2", 1));
        locals.add(new Local("Nombre de local 3", null, null, "Descripcion del local 3", 1));
        locals.add(new Local("Nombre de local 4", null, null, "Descripcion del local 4", 1));
        locals.add(new Local("Nombre de local 5", null, null, "Descripcion del local 5", 1));
        locals.add(new Local("Nombre de local 6", null, null, "Descripcion del local 6", 1));
        return locals;
    }
}

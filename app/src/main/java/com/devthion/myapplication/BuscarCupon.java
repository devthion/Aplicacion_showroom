package com.devthion.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.devthion.myapplication.modelos.Cupon;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuscarCupon extends AppCompatActivity {


    DatabaseReference databaseCupones;
    ArrayList<Cupon> listaCupones;
    RecyclerView rvCupon;
    SearchView searchCupon;
    AdapterCupones adapter;

    LinearLayoutManager lmCupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cupon);

        databaseCupones = FirebaseDatabase.getInstance().getReference("Cupones");
        rvCupon = (RecyclerView) findViewById(R.id.rvCupon);
        searchCupon = findViewById(R.id.searchCupon);
        lmCupon = new LinearLayoutManager(this);
        rvCupon.setLayoutManager(lmCupon);
        listaCupones = new ArrayList<>();
        adapter = new AdapterCupones(listaCupones);
        rvCupon.setAdapter(adapter);


        //MOSTRAMOS TODOS LOS CUPONES DEL FIREBASE CUPONES
        databaseCupones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for(DataSnapshot unCupon: dataSnapshot.getChildren()){
                        Cupon cupon = new Cupon(unCupon.child("Codigo").getValue().toString(),
                                unCupon.child("Local").getValue().toString(),
                                Integer.parseInt(String.valueOf(unCupon.child("Descuento").getValue())),
                                Integer.parseInt(String.valueOf(unCupon.child("Puntos Necesarios").getValue())));

                        Toast.makeText(getApplication(),"CUPON CREADO CON EXITO "+ unCupon.child("Codigo").getValue(),Toast.LENGTH_LONG).show();
                        listaCupones.add(cupon);
                    }

                     adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        searchCupon.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscarCupon(s);
                return true;
            }
        });


    }

    private void buscarCupon(String s) {
        ArrayList<Cupon> miLista = new ArrayList<>();

        for (Cupon obj: listaCupones){
            if(obj.getIdLocal().toLowerCase().contains(s.toLowerCase())) {
                miLista.add(obj);
            }
        }
        AdapterCupones adapter = new AdapterCupones(miLista);
        rvCupon.setAdapter(adapter);
    }
}
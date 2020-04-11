package com.devthion.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.devthion.myapplication.BusquedaShowroom.adapter.AdapterCupones;
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

        databaseCupones = FirebaseDatabase.getInstance().getReference().child("Cupones");
        rvCupon = (RecyclerView) findViewById(R.id.rvCupon);
        //searchCupon = findViewById(R.id.searchCupon);
        lmCupon = new LinearLayoutManager(this);
        rvCupon.setLayoutManager(lmCupon);
        listaCupones = new ArrayList<>();
        adapter = new AdapterCupones(listaCupones);
        rvCupon.setAdapter(adapter);


        //MOSTRAMOS TODOS LOS CUPONES DEL FIREBASE CUPONES
        databaseCupones.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Cupon cupon = snapshot.getValue(Cupon.class);
                        listaCupones.add(cupon);
                    }

                     adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

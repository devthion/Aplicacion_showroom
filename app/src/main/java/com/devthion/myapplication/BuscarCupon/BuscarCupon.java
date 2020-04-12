package com.devthion.myapplication.BuscarCupon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.devthion.myapplication.Administrador.AdminPrincipal;
import com.devthion.myapplication.R;
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
                    //RECORRE TODOS LOS OBJETOS DE CUPONES PARA OBTENER SUS ATRIBUTOS
                    for(DataSnapshot unCupon: dataSnapshot.getChildren()){
                        Cupon cupon = new Cupon(unCupon.getKey(),
                                unCupon.child("Local").getValue().toString(),
                                Integer.parseInt(String.valueOf(unCupon.child("Descuento").getValue())),
                                Integer.parseInt(String.valueOf(unCupon.child("Puntos Necesarios").getValue())));
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


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),
                        "Selecciono el cupon del local "+listaCupones.get(rvCupon.getChildAdapterPosition(view)).getIdCupon(),Toast.LENGTH_SHORT).show();

                alertaEliminar(view);
            }
        });

    }

    public void alertaEliminar(final View view){
        new AlertDialog.Builder(BuscarCupon.this)
                .setTitle("ELIMINAR CUPON")
                .setMessage("¿ESTA SEGURO QUE QUIERE ELIMINAR ESTE CUPON?")
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                Cupon cupon = listaCupones.get(rvCupon.getChildAdapterPosition(view));
                                cupon.eliminarCupon();
                                Intent in = new Intent(view.getContext(), AdminPrincipal.class);
                                startActivity(in);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }).show();
    }

    //ACA EL SEARCHVIEW REALIZA LA BUSQUEDA EN REALTIME POR CADA CARACTER QUE SE ESCRIBE
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

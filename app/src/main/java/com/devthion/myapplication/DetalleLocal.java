package com.devthion.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devthion.myapplication.Interfaces.InterfaceBusquedaCalificacionesDeLocal;
import com.devthion.myapplication.Interfaces.InterfaceBusquedaUnLocal;
import com.devthion.myapplication.Interfaces.InterfaceObtenerPromedioEstrellas;
import com.devthion.myapplication.modelos.Calificacion;

import com.devthion.myapplication.modelos.Local;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;


public class DetalleLocal extends AppCompatActivity implements View.OnClickListener {

    TextView textV_nombreLocal, textV_descripcionLocal, textV_calificacion_promedio;
    RatingBar ratingBarCalificar, ratingBarLocal;
    EditText editT_calificacion;
    Button btn_dar_calificacion;
    BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();
    CalificacionesFirebase calificacionesFirebase = new CalificacionesFirebase();
    Calificacion calificacion;
    int randomIndex;
    String idLocal;
    DecimalFormat dosDecimales = new DecimalFormat("#.##");

    ComentariosRecyclerViewAdaptador calificacionesRecyclerViewAdaptador;
    RecyclerView recyclerView_calificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalle_local);



        textV_descripcionLocal=findViewById(R.id.textV_descripcionLocal);
        btn_dar_calificacion=findViewById(R.id.btn_dar_calificacion);
        ratingBarLocal=findViewById(R.id.ratingBarLocal);
        textV_calificacion_promedio=findViewById(R.id.textV_calificacion_promedio);
        recyclerView_calificaciones =findViewById(R.id.recyclerView_calificaciones);
        recyclerView_calificaciones.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        idLocal = intent.getStringExtra("idLocal");

        calificacionesFirebase.obtenerCalificacionesDeLocal(idLocal, new InterfaceBusquedaCalificacionesDeLocal() {
            @Override
            public void onCallBack(List<Calificacion> calificaciones) {
                calificacionesRecyclerViewAdaptador = new ComentariosRecyclerViewAdaptador(calificaciones);
                recyclerView_calificaciones.setAdapter(calificacionesRecyclerViewAdaptador);
            }
        });


        calificacionesFirebase.obtenerPromedioEstrellas(idLocal, new InterfaceObtenerPromedioEstrellas() {
            @Override
            public void onCallBack(float promedioPuntuacion) {
                ratingBarLocal.setRating(promedioPuntuacion);
                textV_calificacion_promedio.setText(String.format("" + dosDecimales.format(promedioPuntuacion) ));
            }
        });

        busquedaDeLocalesFirebase.busquedaPorId(idLocal, new InterfaceBusquedaUnLocal() {
            @Override
            public void onCallBack(Local unLocal) {
                textV_descripcionLocal.setText(unLocal.getDescripcion());
            }

        });


        btn_dar_calificacion.setOnClickListener(this);



    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_dar_calificacion:
                alertDialog();
            break;
        }
    }

    //CREAR UNA CLASE DE ALERTDIALOG CON METODO "MOSTRAR"
    public void alertDialog(){
        final EditText input = new EditText(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_alert_dialog_calificacion, null);
        editT_calificacion=dialogView.findViewById(R.id.editT_calificacion);
        ratingBarCalificar=dialogView.findViewById(R.id.ratingBarLocal);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)

                .setIcon(android.R.drawable.ic_dialog_alert)

                .setTitle("Are you sure to Exit")

                .setMessage("Exiting will call finish() method")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] usuariosAnonimos = getApplication().getResources().getStringArray(R.array.usuariosAnonimos);
                        randomIndex= new Random().nextInt(usuariosAnonimos.length);
                        calificacion = new Calificacion(usuariosAnonimos[randomIndex]+" Anonimo", editT_calificacion.getText().toString(),idLocal, ratingBarCalificar.getRating() );
                        calificacion.almacenarCalificacion();
                        Toast.makeText(getApplicationContext(),"Calificacion enviada a revision!" + editT_calificacion.getText().toString(),Toast.LENGTH_LONG).show();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(),"Calificacion no enviada!",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }



}

package com.devthion.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;
import com.devthion.myapplication.Interfaces.InterfaceObtencionListaMarkersYTitulos;
import com.devthion.myapplication.modelos.Local;
import com.devthion.myapplication.modelos.TiposEstructuras.Departamento;
import com.devthion.myapplication.modelos.TiposEstructuras.EstructuraLocal;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LocacionesEnMaps extends FragmentActivity implements OnMapReadyCallback {

    DatabaseReference databaseLocales;
    GoogleMap map;
    LinearLayout layoutTop;
    Button btn_home;
    List<LatLng> arrayListMarkers = new ArrayList<>();
    List<String> arrayListTitulosMarkers=new ArrayList<>();

    BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();

    LatLng LatLngCasa = new LatLng(-34.6142496,-58.4843552);
    LatLng LatLngHuerguito = new LatLng(-34.6099674,-58.4565617);
    LatLng LatLngUtnMedrano = new LatLng(-34.6040109,-58.4647395);
    LatLng LatLngUtnCampus = new LatLng(-34.6379249,-58.5114048);
    LatLng LatLngGym = new LatLng(-34.6118624,-58.4950982);
    LatLng LatLngCentroCapitalFederal= new LatLng(-34.6170083,-58.4450927);



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_locaciones_en_maps);

        btn_home =findViewById(R.id.btn_home);
        layoutTop = findViewById(R.id.layout_top);

        //----ANIMACION DE LAYOUT
        layoutTop.animate().alpha(1).translationY(75).setDuration(1100).setStartDelay(250);
        //--------------------

        //
        databaseLocales= FirebaseDatabase.getInstance().getReference("Locales");
        //

        //------------------------ ASIGNO AL "MAP FRAGMENT" LA VISUAL DEL MAPA
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //---------------------------



        /*
        arrayListMarkers.add(LatLngCasa);
        arrayListMarkers.add(LatLngHuerguito);
        arrayListMarkers.add(LatLngUtnMedrano);
        arrayListMarkers.add(LatLngUtnCampus);
        arrayListMarkers.add(LatLngGym);
        //arrayListMarkers y arrayListTitulos, estan definidos en paralelo!!
        arrayListTitulosMarkers.add("Casa");
        arrayListTitulosMarkers.add("Huerguito");
        arrayListTitulosMarkers.add("Medrano");
        arrayListTitulosMarkers.add("Campus");
        arrayListTitulosMarkers.add("Gym");*/

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocacionesEnMaps.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });


    }




    @Override
    public void onMapReady(final GoogleMap googleMap) {
        //tengo que poner el codigo dentro de la funcion de devolucion de la interface para que me tome las dos listas que se llenan
        //en la clase "busquedadelocalesfirebase"
        busquedaDeLocalesFirebase.busquedaMarkersYTitulosDeLocales(new InterfaceObtencionListaMarkersYTitulos() {
            @Override
            public void onCallBack(List<LatLng> listMarkers, List<String> listTitulosMarkers, final List<String> listId) {

                map =googleMap;//NO SE LA VENTAJA DE ESTO... EN EL VIDEO HACIA ESTA ASIGNACION, PERO
                //PUEDO USAR LA VARIABLE "GOOGLEMAP" DE LA MISMA FORMA
                for(int i =0; i<listMarkers.size(); i++){
                    //A CADA LATLNG QUE SE ENCUENTRA EN EL ARRAY DE MARKERS, LAS AGREGO EN EL MAPA Y AL MISMO TIEMPO LE ASIGNO LOS
                    //RESPECTIVOS TITULOS CON UN ARRAY EN PARALELO.

                    Marker marker = map.addMarker(new MarkerOptions().position(listMarkers.get(i)).title(String.valueOf(listTitulosMarkers.get(i)))
                            .snippet("Información extra...")
                            .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.icono_de_local_en_mapa)));
                    marker.setTag(listId.get(i));//paso el id del marker
                    marker.showInfoWindow();//muestro datos del local en el marker

                    /*map.addMarker(marker).showInfoWindow();*/


                }


                //AL ABRIR EL ACTIVITY "ANIMATECAMERA" HACE QUE EL MAPA HAGA ZOOM A UNA LATLNG QUE ESTABLEZCO
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(centroPromedio(listMarkers), 12));


                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        //PERMITE HACER CLICKEABLES LOS MARCADORES EN EL MAPA
                        String markerTitle = marker.getTitle();//TOMA EL TITULO DEL MARCADOR CLICKEADO
                        String id = String.valueOf(marker.getTag());


                        Intent intentDetalleLocal = new Intent(LocacionesEnMaps.this, DetalleLocal.class);
                        intentDetalleLocal.putExtra("nombreLocal", markerTitle);
                        intentDetalleLocal.putExtra("idLocal", id);
                        startActivity(intentDetalleLocal);//ABRE LA ACTIVITY CORRESPONDIENTE AL MARCADOR CLIKEADO Y LE PASA EL TITULO DEL MISMO

                        return false;
                    }
                });

            }
        });


    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        //ESTE METODO ES PARA CAMBIAR EL ASPECTO DEL MARCADOR
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public LatLng centroPromedio(List<LatLng> listMarkers){
        LatLng latlngpromedio = null;

        double latTemp=0;
        double longTemp=0;
        for (int i =0; i<listMarkers.size(); i++){
            latTemp += listMarkers.get(i).latitude;
            longTemp += listMarkers.get(i).longitude;
        }

        latlngpromedio=new LatLng(latTemp/listMarkers.size(),longTemp/listMarkers.size());
        return latlngpromedio;
    }
}

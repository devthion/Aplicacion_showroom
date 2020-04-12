package com.devthion.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class LocacionesEnMaps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    Button btn_home;
    ArrayList<LatLng> arrayListMarkers = new ArrayList<LatLng>();
    LatLng LatLngCasa = new LatLng(-34.6142496,-58.4843552);
    LatLng LatLngHuerguito = new LatLng(-34.6099674,-58.4565617);
    LatLng LatLngUtnMedrano = new LatLng(-34.6040109,-58.4647395);
    LatLng LatLngUtnCampus = new LatLng(-34.6379249,-58.5114048);
    LatLng LatLngGym = new LatLng(-34.6118624,-58.4950982);
    LatLng LatLngCentroCapitalFederal= new LatLng(-34.6170083,-58.4450927);

    ArrayList<String> arrayListTitulosMarkers = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_locaciones_en_maps);

        btn_home =findViewById(R.id.btn_home);

        //------------------------ ASIGNO AL "MAP FRAGMENT" LA VISUAL DEL MAPA
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //---------------------------

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
        arrayListTitulosMarkers.add("Gym");

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocacionesEnMaps.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });


    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        map =googleMap;//NO SE LA VENTAJA DE ESTO... EN EL VIDEO HACIA ESTA ASIGNACION, PERO
                        //PUEDO USAR LA VARIABLE "GOOGLEMAP" DE LA MISMA FORMA
        for(int i =0; i<arrayListMarkers.size(); i++){
            //A CADA LATLNG QUE SE ENCUENTRA EN EL ARRAY DE MARKERS, LAS AGREGO EN EL MAPA Y AL MISMO TIEMPO LE ASIGNO LOS
            //RESPECTIVOS TITULOS CON UN ARRAY EN PARALELO.
            map.addMarker(new MarkerOptions().position(arrayListMarkers.get(i)).title(String.valueOf(arrayListTitulosMarkers.get(i)))
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.icono_de_local_en_mapa)));

        }


        //AL ABRIR EL ACTIVITY "ANIMATECAMERA" HACE QUE EL MAPA HAGA ZOOM A UNA LATLNG QUE ESTABLEZCO
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(centroPromedio(), 12));


       map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
           @Override
           public boolean onMarkerClick(Marker marker) {
               //PERMITE HACER CLICKEABLES LOS MARCADORES EN EL MAPA
               String markerTitle = marker.getTitle();//TOMA EL TITULO DEL MARCADOR CLICKEADO


               Intent intentDetalleLocal = new Intent(LocacionesEnMaps.this, DetalleLocal.class);
               intentDetalleLocal.putExtra("title", markerTitle);
               startActivity(intentDetalleLocal);//ABRE LA ACTIVITY CORRESPONDIENTE AL MARCADOR CLIKEADO Y LE PASA EL TITULO DEL MISMO

               return false;
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

    public LatLng centroPromedio(){
        LatLng latlngpromedio = null;

        double latTemp=0;
        double longTemp=0;
        for (int i =0; i<arrayListMarkers.size(); i++){
            latTemp += arrayListMarkers.get(i).latitude;
            longTemp += arrayListMarkers.get(i).longitude;
        }

        latlngpromedio=new LatLng(latTemp/arrayListMarkers.size(),longTemp/arrayListMarkers.size());
        return latlngpromedio;
    }
}

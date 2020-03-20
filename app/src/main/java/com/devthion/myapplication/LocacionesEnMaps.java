package com.devthion.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

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
    ArrayList<LatLng> arrayListMarkers = new ArrayList<LatLng>();
    LatLng LatLngCasa = new LatLng(-34.6142496,-58.4843552);
    LatLng LatLngHuerguito = new LatLng(-34.6099674,-58.4565617);
    LatLng LatLngUtnMedrano = new LatLng(-34.6040109,-58.4647395);
    LatLng LatLngUtnCampus = new LatLng(-34.6379249,-58.5114048);
    LatLng LatLngGym = new LatLng(-34.6118624,-58.4950982);

    ArrayList<String> arrayListTitulosMarkers = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_locaciones_en_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        arrayListMarkers.add(LatLngCasa);
        arrayListMarkers.add(LatLngHuerguito);
        arrayListMarkers.add(LatLngUtnMedrano);
        arrayListMarkers.add(LatLngUtnCampus);
        arrayListMarkers.add(LatLngGym);

        arrayListTitulosMarkers.add("Casa");
        arrayListTitulosMarkers.add("Huerguito");
        arrayListTitulosMarkers.add("Medrano");
        arrayListTitulosMarkers.add("Campus");
        arrayListTitulosMarkers.add("Gym");



    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        map =googleMap;
        for(int i =0; i<arrayListMarkers.size(); i++){

            map.addMarker(new MarkerOptions().position(arrayListMarkers.get(i)).title(String.valueOf(arrayListTitulosMarkers.get(i)))
                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_info_outline_black_24dp)));

            map.moveCamera(CameraUpdateFactory.newLatLng(arrayListMarkers.get(i)));

        }

       map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
           @Override
           public boolean onMarkerClick(Marker marker) {
               String markerTitle = marker.getTitle();


               Intent intentDetalleLocal = new Intent(LocacionesEnMaps.this, DetalleLocal.class);
               intentDetalleLocal.putExtra("title", markerTitle);
               startActivity(intentDetalleLocal);

               return false;
           }
       });


    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}

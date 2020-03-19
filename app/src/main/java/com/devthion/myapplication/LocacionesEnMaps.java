package com.devthion.myapplication;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocacionesEnMaps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_locaciones_en_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng LatLngcasa = new LatLng(-34.6142496,-58.4843552);

        map.animateCamera(CameraUpdateFactory.newLatLng(LatLngcasa));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLngcasa, 17 ));
        map.addMarker(new MarkerOptions().position(LatLngcasa).title("Casa"));


    }
}

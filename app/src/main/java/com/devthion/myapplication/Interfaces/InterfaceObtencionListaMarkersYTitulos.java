package com.devthion.myapplication.Interfaces;

import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;
import com.devthion.myapplication.modelos.Local;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceObtencionListaMarkersYTitulos {
    void onCallBack(List<LatLng> listMarkers , List<String> listTitulosMarkers, List<String> listId);

}

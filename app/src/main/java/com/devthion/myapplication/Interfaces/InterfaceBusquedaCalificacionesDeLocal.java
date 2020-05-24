package com.devthion.myapplication.Interfaces;

import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;
import com.devthion.myapplication.modelos.Calificacion;

import java.util.List;

public interface InterfaceBusquedaCalificacionesDeLocal {
    void onCallBack(List<Calificacion> calificaciones);
}

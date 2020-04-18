package com.devthion.myapplication.Interfaces;

import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;

import java.io.Serializable;
import java.util.ArrayList;

public interface InterfaceBusquedaLocal extends Serializable {
    void onCallBack(ArrayList<CadenaPorLocal> locales);

}

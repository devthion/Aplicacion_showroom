package com.devthion.myapplication.Interfaces;

import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;

import java.io.Serializable;
import java.util.List;

public interface InterfaceBusquedaLocal extends Serializable {
    void onCallBack(List<CadenaPorLocal> locales);

}

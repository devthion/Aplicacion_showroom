package com.devthion.myapplication.Interfaces;


import com.devthion.myapplication.modelos.Local;

import java.io.Serializable;

public interface InterfaceBusquedaUnLocal extends Serializable {
    void onCallBack(Local unLocal);

}
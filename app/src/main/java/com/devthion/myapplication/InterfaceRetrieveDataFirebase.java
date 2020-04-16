package com.devthion.myapplication;

import com.devthion.myapplication.modelos.Local;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface InterfaceRetrieveDataFirebase extends Serializable {
    void onCallBack(ArrayList<Local> locales);
}

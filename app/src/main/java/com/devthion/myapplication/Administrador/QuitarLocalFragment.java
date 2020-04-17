package com.devthion.myapplication.Administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.devthion.myapplication.BuscarCupon.BuscarCupon;
import com.devthion.myapplication.BuscarLocal.AutoCompleteLocalAdapter;
import com.devthion.myapplication.BuscarLocal.LocalItem;
import com.devthion.myapplication.BusquedaDeLocalesFirebase;
import com.devthion.myapplication.InterfaceRetrieveDataFirebase;
import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


public class QuitarLocalFragment extends Fragment {
    private ArrayList<Local> localList;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_quitar_local, container, false);
        //Intent in = new Intent(getActivity(), BuscarCupon.class);
        //startActivity(in);

        BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();
        busquedaDeLocalesFirebase.busquedaLocales(new InterfaceRetrieveDataFirebase() {
            @Override
            public void onCallBack(ArrayList<Local> locales) {
                if (locales==null || locales.isEmpty()){
                    Toast.makeText(getActivity(),"LISTA VACIA",Toast.LENGTH_LONG).show();

                }else {
                    AutoCompleteTextView editText = view.findViewById(R.id.autoCompBusquedaGral);
                    AutoCompleteLocalAdapter adapter = new AutoCompleteLocalAdapter(getActivity(),locales);

                    editText.setAdapter(adapter);
                }
            }
        });
        return view;
    }
}

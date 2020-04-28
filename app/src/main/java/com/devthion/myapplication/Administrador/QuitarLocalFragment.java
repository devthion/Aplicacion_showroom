package com.devthion.myapplication.Administrador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.devthion.myapplication.BuscarLocal.AutoCompleteLocalAdapter;
import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;
import com.devthion.myapplication.BusquedaDeLocalesFirebase;
import com.devthion.myapplication.Interfaces.InterfaceBusquedaLocal;
import com.devthion.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


public class QuitarLocalFragment extends Fragment {
    private ArrayList<CadenaPorLocal> localList;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_quitar_local, container, false);
        //Intent in = new Intent(getActivity(), BuscarCupon.class);
        //startActivity(in);

        BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();
        busquedaDeLocalesFirebase.busquedaPorCadena(new InterfaceBusquedaLocal() {
            @Override
            public void onCallBack(List<CadenaPorLocal> locales) {
                if (locales==null || locales.isEmpty()){
                    Toast.makeText(getActivity(),"LISTA VACIA",Toast.LENGTH_LONG).show();

                }else {
                    AutoCompleteTextView editText = view.findViewById(R.id.autoCompBusquedaGral);
                    AutoCompleteLocalAdapter adapter = new AutoCompleteLocalAdapter(getContext(),locales);

                    editText.setAdapter(adapter);
                }
            }
        });
        return view;
    }
}

package com.devthion.myapplication.Administrador;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.devthion.myapplication.BuscarCupon.BuscarCupon;
import com.devthion.myapplication.BuscarLocal.AutoCompleteLocalAdapter;
import com.devthion.myapplication.BuscarLocal.LocalItem;
import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;


public class QuitarLocalFragment extends Fragment {
    private List<LocalItem> localList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quitar_local, container, false);
        //Intent in = new Intent(getActivity(), BuscarCupon.class);
        //startActivity(in);

        fillLocalList();

        AutoCompleteTextView editText = view.findViewById(R.id.autoCompBusquedaGral);
        AutoCompleteLocalAdapter adapter = new AutoCompleteLocalAdapter(container.getContext(),localList);

        editText.setAdapter(adapter);

        return view;
    }

    private void fillLocalList(){
        localList = new ArrayList<>();
        localList.add(new LocalItem("Showroom1"));
        localList.add(new LocalItem("Local2"));
        localList.add(new LocalItem("lala"));
        localList.add(new LocalItem("Prueba"));
        localList.add(new LocalItem("Otro mas"));
        localList.add(new LocalItem("YAYO"));
    }


}

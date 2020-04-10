package com.devthion.myapplication.Administrador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.R;
import com.devthion.myapplication.modelos.Local;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;



public class AgregarLocalFragment extends Fragment {

    Button btnGuardarLocal;
    TextView etNombre, etCalle, etNumero, etCodPostal, etBarrio;
    CheckBox checkHombre, checkMujer,checkNiños;
    List<String> tipos = new ArrayList<>();
    //Local nuevoLocal;
    Spinner spinnerTipoLocal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_agregar_local, container, false);


        btnGuardarLocal = (Button) view.findViewById(R.id.btnGuardarLocal);
        etNombre = (TextView) view.findViewById(R.id.etNombreLocal);
        etCalle = (TextView) view.findViewById(R.id.etCalle);
        etNumero = (TextView) view.findViewById(R.id.etNumero);
        etCodPostal = (TextView) view.findViewById(R.id.etCodPostal);
        etBarrio = (TextView) view.findViewById(R.id.etBarrio);
        checkHombre = (CheckBox) view.findViewById(R.id.checkRopaHombre);
        checkMujer = (CheckBox) view.findViewById(R.id.checkRopaMujer);
        checkNiños = (CheckBox) view.findViewById(R.id.checkRopaNiños);
        spinnerTipoLocal = (Spinner) view.findViewById(R.id.spTipoLocal);
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.opcionesLocal,android.R.layout.simple_spinner_item);
        */
        List<String> tiposLocales = new ArrayList<>();
        tiposLocales.add("Departamento");
        tiposLocales.add("Local A Calle");
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,tiposLocales);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, tiposLocales);

        /*
        spinnerTipoLocal.setAdapter(adapter);
        spinnerTipoLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapterView.getContext(),"Selecciono: "+adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT);
                String tipoLocal = adapterView.getItemAtPosition(i).toString();
                etNumero.setText(""+tipoLocal);
            }
        });
*/


        btnGuardarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLocales(tipos);
                //nuevoLocal = generarLocal();

                //Local.guardarLocal(etNombre.getText().toString(),etCalle.getText().toString(),Integer.parseInt(etNumero.getText().toString()),Integer.parseInt(etCodPostal.getText().toString()),etBarrio.getText().toString(),tipos);
                Toast.makeText(getContext(),"LOCAL CREADO CON EXITO",Toast.LENGTH_LONG).show();

                etNombre.setText("");
                etCodPostal.setText("");
                etNumero.setText("");
                etBarrio.setText("");
                etCalle.setText("");



            }
        });

        return view;

    }

    public void validarLocales(List pistas){
        String resultado = null;
        if(checkMujer.isChecked()){
             resultado = "Ropa Mujer";
        }
        if(checkHombre.isChecked()){
            resultado = "Ropa Hombre";
        }
        if(checkNiños.isChecked()){
            resultado = "Ropa Niños";
        }
    }


}

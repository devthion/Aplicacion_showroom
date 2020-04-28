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
import com.devthion.myapplication.modelos.TiposEstructuras.Departamento;
import com.devthion.myapplication.modelos.TiposEstructuras.EstructuraLocal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;



public class AgregarLocalFragment extends Fragment {

    Button btnGuardarLocal;
    TextView etNombre, etCalle, etNumero, etCodPostal, etBarrio, etPiso, etDepartamento,etTelefono,etDescripcion, etLinkInsta, etLinkWeb;
    CheckBox checkHombre, checkMujer,checkNiños,checkUnisex,checkAccesorios,checkCalzado;
    List<String> tipos = new ArrayList<>();
    Local nuevoLocal;
    Spinner spinnerTipoLocal;
    String idLocal;
    DatabaseReference databaseLocales;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_agregar_local, container, false);

        databaseLocales= FirebaseDatabase.getInstance().getReference("Locales");
        btnGuardarLocal = (Button) view.findViewById(R.id.btnGuardarLocal);

        etNombre = (TextView) view.findViewById(R.id.etNombreLocal);
        etCalle = (TextView) view.findViewById(R.id.etCalle);
        etNumero = (TextView) view.findViewById(R.id.etNumero);
        etCodPostal = (TextView) view.findViewById(R.id.etCodPostal);
        etBarrio = (TextView) view.findViewById(R.id.etBarrio);
        etPiso = (TextView) view.findViewById(R.id.etPiso);
        etDepartamento = (TextView) view.findViewById(R.id.etDepto);
        etTelefono = (TextView) view.findViewById(R.id.etTelefono);
        etDescripcion = (TextView) view.findViewById(R.id.etDescripcion);
        etLinkInsta = (TextView) view.findViewById(R.id.etLinkInstagram);
        etLinkWeb = (TextView) view.findViewById(R.id.etLinkSitioWeb);

        checkHombre = (CheckBox) view.findViewById(R.id.checkRopaHombre);
        checkMujer = (CheckBox) view.findViewById(R.id.checkRopaMujer);
        checkNiños = (CheckBox) view.findViewById(R.id.checkRopaNiños);
        checkUnisex = (CheckBox) view.findViewById(R.id.checkUnisex);
        checkAccesorios = (CheckBox) view.findViewById(R.id.checkAccesorios);
        checkCalzado = (CheckBox) view.findViewById(R.id.checkCalzado);

        spinnerTipoLocal = (Spinner) view.findViewById(R.id.spTipoLocal);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(container.getContext(),
                R.array.opcionesLocal,android.R.layout.simple_spinner_item);

        spinnerTipoLocal.setAdapter(adapter);
/*        spinnerTipoLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"Selecciono: "+adapterView.getItemAtPosition(i),Toast.LENGTH_SHORT);
                String tipoLocal = adapterView.getItemAtPosition(i).toString();
                etNumero.setText(""+tipoLocal);
            }
        });

*/
        btnGuardarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarLocales(tipos);
                nuevoLocal = generarLocal(tipos);
                nuevoLocal.almacenarLocal();
                Toast.makeText(getContext(),"LOCAL CREADO CON EXITO",Toast.LENGTH_LONG).show();
                limpiarFragment();

            }
        });

        return view;

    }

    private Local generarLocal(List<String> categorias) {
        Local nuevoLocal;
        EstructuraLocal nuevaEstructura;

        String nombreLocal = etNombre.getText().toString();
        String calleLocal = etCalle.getText().toString();
        int numeroCalle = Integer.parseInt(etNumero.getText().toString());
        int pisoDepto = Integer.parseInt(etPiso.getText().toString());
        int departamento = Integer.parseInt(etDepartamento.getText().toString());
        int codigoPostal = Integer.parseInt(etCodPostal.getText().toString());
        String barrioLocal = etBarrio.getText().toString();
        String descripcionLocal = etDescripcion.getText().toString();
        int telefono = Integer.parseInt(etTelefono.getText().toString());
        String instagram = etLinkInsta.getText().toString();
        String sitioWeb = etLinkWeb.getText().toString();

        idLocal = databaseLocales.push().getKey();

        nuevaEstructura = new Departamento(calleLocal,numeroCalle,pisoDepto,departamento,barrioLocal,codigoPostal);
        nuevoLocal = new Local(idLocal,nombreLocal,nuevaEstructura,categorias,descripcionLocal,telefono,instagram,sitioWeb);

        return nuevoLocal;
    }

    public void limpiarFragment(){
        etNombre.setText("");
        etCodPostal.setText("");
        etNumero.setText("");
        etBarrio.setText("");
        etCalle.setText("");
        etPiso.setText("");
        etDepartamento.setText("");
        etDescripcion.setText("");
        etTelefono.setText("");
        etLinkInsta.setText("");
        etLinkWeb.setText("");
    }

    public void validarLocales(List tipos){
        if(checkMujer.isChecked()){
             tipos.add("Ropa Mujer");
        }
        if(checkHombre.isChecked()){
            tipos.add("Ropa Hombre");
        }
        if(checkNiños.isChecked()){
            tipos.add("Ropa Niños");
        }
        if(checkUnisex.isChecked()){
            tipos.add("Ropa Unisex");
        }
        if(checkAccesorios.isChecked()){
            tipos.add("Accesorios");
        }
        if(checkCalzado.isChecked()){
            tipos.add("Calzado");
        }
    }


}

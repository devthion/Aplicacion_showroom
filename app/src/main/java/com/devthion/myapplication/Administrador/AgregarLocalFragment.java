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
import com.devthion.myapplication.modelos.TiposEstructuras.LocalACalle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;



public class AgregarLocalFragment extends Fragment {

    Button btnGuardarLocal;
    TextView etNombre, etCalle, etNumero, etCodPostal, etBarrio, etPiso, etDepartamento,etTelefono,etDescripcion, etLinkInsta, etLinkWeb, etNumeroLocal;
    CheckBox checkHombre, checkMujer,checkNiños,checkUnisex,checkAccesorios,checkCalzado, checkRopaBanio, checkAbrigo,checkEnvio;
    List<String> tipos = new ArrayList<>();
    Local nuevoLocal;
    Spinner spinnerTipoLocal;
    String idLocal;
    DatabaseReference databaseLocales;
    int tipoDeLocal=0;


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
        etNumeroLocal = (TextView) view.findViewById(R.id.etNumeroLocal);

        checkHombre = (CheckBox) view.findViewById(R.id.checkRopaHombre);
        checkMujer = (CheckBox) view.findViewById(R.id.checkRopaMujer);
        checkNiños = (CheckBox) view.findViewById(R.id.checkRopaNiños);
        checkUnisex = (CheckBox) view.findViewById(R.id.checkUnisex);
        checkAccesorios = (CheckBox) view.findViewById(R.id.checkAccesorios);
        checkCalzado = (CheckBox) view.findViewById(R.id.checkCalzado);
        checkRopaBanio = (CheckBox) view.findViewById(R.id.checkRopaBanio);
        checkAbrigo = (CheckBox) view.findViewById(R.id.checkAbrigo);

        checkEnvio = (CheckBox) view.findViewById(R.id.checkEnvio);

        spinnerTipoLocal = (Spinner) view.findViewById(R.id.spTipoLocal);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(container.getContext(),
                R.array.opcionesLocal,android.R.layout.simple_spinner_item);

        spinnerTipoLocal.setAdapter(adapter);
        instanciarAgregarLocal(view);

        spinnerTipoLocal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        etDepartamento.setVisibility(view.VISIBLE);
                        etPiso.setVisibility(view.VISIBLE);
                        etNumeroLocal.setVisibility(view.GONE);
                        tipoDeLocal = 0;
                        break;
                    case 1:
                        etNumeroLocal.setVisibility(view.VISIBLE);
                        etDepartamento.setVisibility(view.GONE);
                        etPiso.setVisibility(view.GONE);
                        tipoDeLocal = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
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
        boolean envio = haceEnvio();
        String nombreLocal = etNombre.getText().toString();
        String calleLocal = etCalle.getText().toString();
        int numeroCalle = Integer.parseInt(etNumero.getText().toString());
        int numeroDeLocal = Integer.parseInt(etNumeroLocal.getText().toString());
        int departamento = Integer.parseInt(etDepartamento.getText().toString());
        int pisoDepto = Integer.parseInt(etPiso.getText().toString());
        int codigoPostal = Integer.parseInt(etCodPostal.getText().toString());
        String barrioLocal = etBarrio.getText().toString();
        String descripcionLocal = etDescripcion.getText().toString();
        int telefono = Integer.parseInt(etTelefono.getText().toString());
        String instagram = etLinkInsta.getText().toString();
        String sitioWeb = etLinkWeb.getText().toString();

        idLocal = databaseLocales.push().getKey();

        if(tipoDeLocal==0){
            nuevaEstructura = new Departamento(calleLocal,numeroCalle,pisoDepto,departamento,barrioLocal,codigoPostal);
        }else{
            nuevaEstructura = new LocalACalle(calleLocal,numeroCalle,numeroDeLocal,barrioLocal,codigoPostal);
        }

        nuevoLocal = new Local(idLocal,nombreLocal,nuevaEstructura,categorias,descripcionLocal,telefono,instagram,sitioWeb, envio);

        nuevoLocal.setContext(getContext()); // PASO EL CONTEXT PARA QUE LO USE GEOCODER
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

    public void instanciarAgregarLocal(View view){
        //etNumeroLocal.setVisibility(view.GONE);
        etDepartamento.setVisibility(view.GONE);
        etPiso.setVisibility(view.GONE);
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
        if(checkRopaBanio.isChecked()){
            tipos.add("Ropa Baño");
        }
        if(checkAbrigo.isChecked()){
            tipos.add("Abrigo");
        }
    }

    public boolean haceEnvio(){
        if(checkEnvio.isChecked()){
            return true;
        }else{
            return false;
        }
    }


}

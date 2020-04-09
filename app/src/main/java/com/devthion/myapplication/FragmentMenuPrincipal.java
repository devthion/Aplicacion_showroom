package com.devthion.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.devthion.myapplication.ingreso.InicioSesion;
import com.devthion.myapplication.ingreso.VerificarEmail;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.res.Resources.getSystem;


public class FragmentMenuPrincipal extends Fragment  {

    ImageView fondo_menu_principal;
    LinearLayout textVMenu;
    ConstraintLayout constraintLayout_menu;
    FirebaseAuth fAuth;
    Button btnCerrarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_menu_principal, container, false);
        btnCerrarSesion = (Button) view.findViewById(R.id.btnCerrarSesionn);
        fAuth = FirebaseAuth.getInstance();
        fondo_menu_principal = view.findViewById(R.id.fondo_menu_principal);
        textVMenu =view.findViewById(R.id.linearMenu);
        constraintLayout_menu = view.findViewById(R.id.constraintLayout_menu);


        //obtengo el alto de la pantalla del dispositivo, para setear bien cuanto se tiene que mover la animacion
        int h = Resources.getSystem().getDisplayMetrics().heightPixels;

        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");




        fondo_menu_principal.animate().translationY((float) (-h*0.65 )).setDuration(1000).setStartDelay(300);
        textVMenu.animate().translationY(140).alpha(0).setDuration(1200).setStartDelay(280);
        constraintLayout_menu.animate().alpha(1).setDuration(800).setStartDelay(300);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fAuth.signOut();

                Toast.makeText(getActivity(), "CERRAR SESION", Toast.LENGTH_SHORT).show();
                //Intent in = new Intent(getActivity(), Perfil.class);
                //startActivity(in);
            }
        });



        return view;
    }

}

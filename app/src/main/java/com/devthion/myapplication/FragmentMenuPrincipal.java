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

    ImageView flor3, flor2;
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
        flor3 = view.findViewById(R.id.flor3);
        flor2 = view.findViewById(R.id.flor2);
        textVMenu =view.findViewById(R.id.linearMenu);
        constraintLayout_menu = view.findViewById(R.id.constraintLayout_menu);


        //obtengo el alto de la pantalla del dispositivo, para setear bien cuanto se tiene que mover la animacion
        int h = Resources.getSystem().getDisplayMetrics().heightPixels;

        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");




        flor3.animate().translationX(-100).alpha(0).setDuration(600).setStartDelay(300);
        flor2.animate().translationX(100).alpha(0).setDuration(600).setStartDelay(300);
        textVMenu.animate().scaleX(2).scaleY(2).alpha(0).setDuration(500).setStartDelay(300);
        constraintLayout_menu.animate().alpha(1).setDuration(800).setStartDelay(500);

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

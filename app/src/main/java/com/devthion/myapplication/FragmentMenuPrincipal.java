package com.devthion.myapplication;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.devthion.myapplication.ingreso.InicioSesion;
import com.devthion.myapplication.ingreso.VerificarEmail;
import com.devthion.myapplication.modelos.SliderMenuPrincipal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Resources.getSystem;


public class FragmentMenuPrincipal extends Fragment  {

    ImageView flor3, flor2;
    LinearLayout textVMenu;
    ConstraintLayout constraintLayout_menu;
    FirebaseAuth fAuth;
    Button btnCerrarSesion;

    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<SliderMenuPrincipal> sliderMenuPrincipals;

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
                fAuth.signOut();

                Toast.makeText(getActivity(), "CERRAR SESION", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getActivity(), Perfil.class);
                startActivity(in);
            }
        });




        //SLIDER------------------------

        sliderMenuPrincipals = new ArrayList<>();
        sliderMenuPrincipals.add(new SliderMenuPrincipal(R.drawable.brochure, "Titulo 1", "Descripcion 1"));
        sliderMenuPrincipals.add(new SliderMenuPrincipal(R.drawable.sticker, "Titulo 2", "Descripcion 2"));
        sliderMenuPrincipals.add(new SliderMenuPrincipal(R.drawable.poster, "Titulo 3", "Descripcion 3"));
        sliderMenuPrincipals.add(new SliderMenuPrincipal(R.drawable.namecard, "Titulo 4", "Descripcion 4"));

        adapter = new AdapterSliderMenuPrincipal(sliderMenuPrincipals, getActivity());

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter((PagerAdapter) adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position<(adapter.getCount()-1) && position < (colors.length -1)){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position+1]));
                }else {
                    viewPager.setBackgroundColor(colors[colors.length -1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //------------------------------



        return view;
    }

}

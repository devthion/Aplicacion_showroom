package com.devthion.myapplication;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.devthion.myapplication.BuscarLocal.AutoCompleteLocalAdapter;
import com.devthion.myapplication.BuscarLocal.CadenaPorLocal;
import com.devthion.myapplication.Interfaces.InterfaceBusquedaLocal;
import com.devthion.myapplication.ingreso.InicioSesion;
import com.devthion.myapplication.ingreso.VerificarEmail;
import com.devthion.myapplication.modelos.SliderMenuPrincipal;
import com.devthion.myapplication.modelos.UploadFotoPerfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Resources.getSystem;


public class FragmentMenuPrincipal extends Fragment implements NavigationView.OnNavigationItemSelectedListener  {

    ImageView flor3, flor2;
    LinearLayout textVMenu, layout_header_menuprincipal;
    ConstraintLayout constraintLayout_menu, constraintLayout;
    FirebaseAuth fAuth;



    ViewPager viewPager;
    Adapter adapter;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<SliderMenuPrincipal> sliderMenuPrincipals;
    BottomNavigationView bottomNavigationView;
    AutoCompleteTextView buscadorLocal;


    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ArrayList<CadenaPorLocal> localList;

    TextView textVNombreDeUsuario, textVMailDeUsuario;
    ImageView imagenPerfil;
    DatabaseReference databaseReference;
    public BusquedaDeLocalesFirebase busquedaDeLocalesFirebase = new BusquedaDeLocalesFirebase();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_fragment_menu_principal, container, false);
        /*btnCerrarSesion = (Button) view.findViewById(R.id.btnCerrarSesionn);*/
        fAuth = FirebaseAuth.getInstance();
        flor3 = view.findViewById(R.id.flor3);
        flor2 = view.findViewById(R.id.flor2);
        textVMenu =view.findViewById(R.id.linearMenu);
        constraintLayout_menu = view.findViewById(R.id.constraintLayout_menu);
        constraintLayout=view.findViewById(R.id.constraintLayout);
        layout_header_menuprincipal = view.findViewById(R.id.layout_header_menuprincipal);



        //SETTING MENU LATERAL

        drawerLayout = view.findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.nav_view);


        //INSTANCIAMOS LA BD DATA PARA LAS FOTOS DE PERFIL EN FIREBASE
        databaseReference = FirebaseDatabase.getInstance().getReference("fotos_perfil");


        //INSTANCIAMOS LA BD DE AUTHENTICATION (ES LA BD DONDE ESTAN LOS DATOS DEL EMAIL)
        fAuth = FirebaseAuth.getInstance();

        //ACA OBTENEMOS EL ID DEL USUARIO DE LA BD AUTHENTICATION
        String userName = fAuth.getCurrentUser().getProviderId();
        final String userID = fAuth.getCurrentUser().getUid();
        String userMail = fAuth.getCurrentUser().getEmail();

        //BUSCADOR GENERAL
        //------------------------
        buscadorLocal = view.findViewById(R.id.autoCompBusquedaGral);
        busquedaDeLocalesFirebase.busquedaPorCadena(new InterfaceBusquedaLocal() {
            @Override
            public void onCallBack(List<CadenaPorLocal> locales) {

                AutoCompleteLocalAdapter adapterLocal = new AutoCompleteLocalAdapter(getActivity(),locales);
                buscadorLocal.setAdapter(adapterLocal);

            }
        });

        buscadorLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Selecciono un showroom"+buscadorLocal.getText().toString(),Toast.LENGTH_LONG ).show();
            }
        });

        //TE MANDA AL DETALLE DEL LOCAL SELECCIONADO
        buscadorLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CadenaPorLocal local = (CadenaPorLocal) adapterView.getAdapter().getItem(i);
                String markerTitle = local.getNombreLocal();
                String id = local.getIdLocal();

                Intent intentDetalleLocal = new Intent(getContext(), DetalleLocal.class);
                intentDetalleLocal.putExtra("nombreLocal", markerTitle);
                intentDetalleLocal.putExtra("idLocal", id);
                startActivity(intentDetalleLocal);//ABRE LA ACTIVITY CORRESPONDIENTE AL MARCADOR CLIKEADO Y LE PASA EL TITULO DEL MISMO

                buscadorLocal.setText("");
            }
        });


        //------------------------


        //DE ESTA MANERA ACCEDO DESDE EL NAV_VIEW AL HEADER_VIEW DEL CUAL OBTENGO LOS OBJETOS QUE DESEO MODIFICAR
        navigationView = view.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        textVNombreDeUsuario = headerView.findViewById(R.id.textV_nombre_usuario);
        textVMailDeUsuario = headerView.findViewById(R.id.textV_mail_usuario);
        imagenPerfil = headerView.findViewById(R.id.imagenPerfil);
        textVMailDeUsuario.setText(userMail);
        textVNombreDeUsuario.setText(userName);


        //OBTENEMOS LA FOTO DE PERFIL

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot unaFoto : dataSnapshot.getChildren()){
                    if(unaFoto.getKey().equals(userID)){
                        UploadFotoPerfil uploadFotoPerfil = unaFoto.getValue(UploadFotoPerfil.class);
                        Picasso.get().load(uploadFotoPerfil.getImageUrl()).fit().into(imagenPerfil);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        init();

        //---------------


        //SETTING BOTTOM NAV VIEW

        bottomNavigationView = view.findViewById(R.id.bottom_nav_view);

        bottomNavigationView.setSelectedItemId(R.id.Home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){

                    case R.id.Menu:
                        drawerLayout.openDrawer(GravityCompat.START);
                        return true;
                    case R.id.Home:
                        intent = new Intent(getActivity(), MenuPrincipal.class);
                        startActivity(intent);
                        return true;
                    case R.id.Mapa:
                        intent = new Intent(getActivity(), LocacionesEnMaps.class);
                        startActivity(intent);
                        return true;
                    case R.id.Buscador:
                        //TODO
                        //intent = new Intent(getActivity(), mFragmentFavorite.class);
                        //startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        //----------------------------------------------




        //ANIMACIONES
        layout_header_menuprincipal.animate().alpha(1).setDuration(600).setStartDelay(300);
        flor3.animate().translationX(-100).alpha(0).setDuration(600).setStartDelay(300);
        flor2.animate().translationX(100).alpha(0).setDuration(600).setStartDelay(300);
        textVMenu.animate().scaleX(2).scaleY(2).alpha(0).setDuration(500).setStartDelay(300);
        constraintLayout_menu.animate().alpha(1).setDuration(800).setStartDelay(500);
        //---------------------------------------------------






        //SLIDER------------------------

        sliderMenuPrincipals = new ArrayList<>();
        sliderMenuPrincipals.add(new SliderMenuPrincipal(R.drawable.brochure, "Ropa De Hombre", "Descubri todos los locales para ropa de hombre"));
        sliderMenuPrincipals.add(new SliderMenuPrincipal(R.drawable.sticker, "Ropa De Niños", "Encontra todo lo necesario para tu niño"));
        sliderMenuPrincipals.add(new SliderMenuPrincipal(R.drawable.poster, "Ropa UNISEX", "Ropa para todos"));
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
                    constraintLayout.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position+1]));
                }else {
                    viewPager.setBackgroundColor(colors[colors.length -1]);
                    constraintLayout.setBackgroundColor(colors[colors.length -1]);
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }else {
                    return false;
                }
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void init(){
        //LE DA EL DISEÑO, DEFINE EL NAVCONTROLLER Y SUS PROPIEDADES
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // SETEA LAS ACTIVITYS A LAS QUE IR CUANDO SE CLICKEA SOBRE UNA OPCION DEL MENU
        switch (menuItem.getItemId()){
            case R.id.nav_maps:{

                if(isValidDestination(R.id.nav_maps)) {
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.mapsScreen);
                }

                break;
            }
            case R.id.nav_perfil:{

                if(isValidDestination(R.id.nav_perfil)) {
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.perfilScreen);
                }

                break;
            }
            case R.id.nav_cupones:{

                if(isValidDestination(R.id.nav_cupones)) {
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.cuponesScreen);
                }

                break;
            }
            case R.id.nav_menu:{
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.main, true).build();
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.menuScreen, null, navOptions);

                break;
            }

            case R.id.nav_cerrar_sesion:{
                new AlertDialog.Builder(getActivity())
                        .setTitle("Cerrar Sesion")
                        .setMessage("Deseas cerrar sesion?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                fAuth.signOut();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("No", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();



                break;
            }
        }

        menuItem.setChecked(false);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private boolean isValidDestination(int destination){
        //CHEQUEA QUE LA ACTIVITY A LA QUE QUIERO IR EN EL MENU, NO SE EN LA QUE ESTOY
        return destination!=Navigation.findNavController(getActivity(), R.id.nav_host_fragment).getCurrentDestination().getId();
    }


    public boolean onSupportNavigateUp() {
        //CUANDO VUELVO PARA ATRAS, CIERRA EL FRAGMENT EN EL QUE ESTOY
        return NavigationUI.navigateUp(Navigation.findNavController(getActivity(), R.id.nav_host_fragment), drawerLayout);
    }


}


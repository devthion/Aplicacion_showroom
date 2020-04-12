package com.devthion.myapplication.Administrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.devthion.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class AdminPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin_principal);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        init();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         return super.onOptionsItemSelected(item);
    }

    private void init(){
        //LE DA EL DISEÑO, DEFINE EL NAVCONTROLLER Y SUS PROPIEDADES
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       // NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        // SETEA LAS ACTIVITYS A LAS QUE IR CUANDO SE CLICKEA SOBRE UNA OPCION DEL MENU
        switch (menuItem.getItemId()){
            case R.id.nav_home:{
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.main_menu_admin, true).build();
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.adminHomeScreen, null, navOptions);

                break;
            }
            case R.id.nav_agregar_local:{

                if(isValidDestination(R.id.nav_agregar_local)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.agregarLocalScreen);
                }

                break;
            }
            case R.id.nav_quitar_local:{

                if(isValidDestination(R.id.nav_quitar_local)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.quitarLocalScreen);
                }

                break;
            }
            case R.id.nav_agregar_cupon:{

                if(isValidDestination(R.id.nav_agregar_cupon)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.agregarCuponScreen);
                }

                break;
            }

        }

        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private boolean isValidDestination(int destination){
        //CHEQUEA QUE LA ACTIVITY A LA QUE QUIERO IR EN EL MENU, NO SE EN LA QUE ESTOY
        return destination!=Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    @Override
    public boolean onSupportNavigateUp() {
        //CUANDO VUELVO PARA ATRAS, CIERRA EL FRAGMENT EN EL QUE ESTOY
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }



}
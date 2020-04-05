package com.devthion.myapplication;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.navigation.NavigationView;

public class MenuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_principal);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //drawerLayout.openDrawer(GravityCompat.START); //con esto abro el drawer, lo dejo para saber como se usa



        init();


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

    private void init(){
        //LE DA EL DISEÑO, DEFINE EL NAVCONTROLLER Y SUS PROPIEDADES
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // SETEA LAS ACTIVITYS A LAS QUE IR CUANDO SE CLICKEA SOBRE UNA OPCION DEL MENU
        switch (menuItem.getItemId()){
            case R.id.nav_maps:{

                if(isValidDestination(R.id.nav_maps)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.mapsScreen);
                }

                break;
            }
            case R.id.nav_perfil:{

                if(isValidDestination(R.id.nav_perfil)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.perfilScreen);
                }

                break;
            }
            case R.id.nav_cupones:{

                if(isValidDestination(R.id.nav_cupones)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.cuponesScreen);
                }

                break;
            }
            case R.id.nav_menu:{
                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.main, true).build();
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.menuScreen, null, navOptions);

                break;
            }
        }

        menuItem.setChecked(false);
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

package com.devthion.myapplication;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import com.devthion.myapplication.modelos.UploadFotoPerfil;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.zip.Inflater;

public class MenuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    FirebaseAuth fAuth;
    TextView textVNombreDeUsuario, textVMailDeUsuario;
    ImageView imagenPerfil;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_principal);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //drawerLayout.openDrawer(GravityCompat.START); //con esto abro el drawer, lo dejo para saber como se usa

        //INSTANCIAMOS LA BD DATA PARA LAS FOTOS DE PERFIL EN FIREBASE
        databaseReference = FirebaseDatabase.getInstance().getReference("fotos_perfil");


        //INSTANCIAMOS LA BD DE AUTHENTICATION (ES LA BD DONDE ESTAN LOS DATOS DEL EMAIL)
        fAuth = FirebaseAuth.getInstance();

        //ACA OBTENEMOS EL ID DEL USUARIO DE LA BD AUTHENTICATION
        String userName = fAuth.getCurrentUser().getProviderId();
        final String userID = fAuth.getCurrentUser().getUid();
        String userMail = fAuth.getCurrentUser().getEmail();



        //DE ESTA MANERA ACCEDO DESDE EL NAV_VIEW AL HEADER_VIEW DEL CUAL OBTENGO LOS OBJETOS QUE DESEO MODIFICAR
        navigationView = findViewById(R.id.nav_view);
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
                Toast.makeText(MenuPrincipal.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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
        //NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
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

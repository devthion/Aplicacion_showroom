package com.devthion.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Perfil extends AppCompatActivity {

    FirebaseAuth fAuth;
    ProgressBar progressBar;
    String userID,userNombre;
    TextView txtId,etPerfilNombre;
    //DECLARAMOS LA BASE DE DATOS DE LOS USUARIOS
    FirebaseFirestore databaseUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfil);


        txtId =(TextView) findViewById(R.id.txtPerfilId);
        etPerfilNombre =(TextView) findViewById(R.id.etPerfilNombre);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);



        txtId.setVisibility(View.GONE);//INICIALMENTE SETEO AMBOS EN GONE PARA DAR TIEMPO
        etPerfilNombre.setVisibility(View.GONE); //A QUE SE CARGUEN DESDE FIREBASE


        //INSTANCIAMOS LA BD DE LOS USUARIOS
        databaseUsuarios = FirebaseFirestore.getInstance();
        //INSTANCIAMOS LA BD DE AUTHENTICATION (ES LA BD DONDE ESTAN LOS DATOS DEL EMAIL)
        fAuth = FirebaseAuth.getInstance();

        //ACA OBTENEMOS EL ID DEL USUARIO DE LA BD AUTHENTICATION
        userID = fAuth.getCurrentUser().getUid();
        txtId.setText("ID "+userID);


        //HACEMOS REFERENCIA A LA BD USUARIOS Y NOS COLOCAMOS EN LOS DATOS DEL ID QUE OBTUVIMOS ANTERIORMENTE
        DocumentReference documentReference = databaseUsuarios.collection("Usuarios").document(userID);


        //OBTENEMOS LOS DATOS DEL DOCUMENT REFERENCE
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    //USAMOS EL GET DATA PARA AGARRAR LOS DATOS
                    userNombre = (String) documentSnapshot.getData().get("Nombre");
                    etPerfilNombre.setText("NOMBRE DEL USUARIO " + userNombre);

                    //----------------------------------
                    //a la visibilidad del progressbar la pongo en GONE para que ya no se vea
                    //y hago visibles los datos que ya cargaron
                    progressBar.setVisibility(View.GONE);
                    etPerfilNombre.setVisibility(View.VISIBLE);
                    txtId.setVisibility(View.VISIBLE);
                    //------------------------------------

                }



            }
        });




    }
}

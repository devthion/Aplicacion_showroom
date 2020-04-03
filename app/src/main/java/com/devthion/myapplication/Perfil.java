package com.devthion.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devthion.myapplication.modelos.UploadFotoPerfil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.net.URI;
import java.util.ArrayList;

public class Perfil extends AppCompatActivity {

    FirebaseAuth fAuth;
    ProgressBar progressBar, progressBarImagenPerfil;
    String userID,userNombre;
    TextView txtId,etPerfilNombre;
    //DECLARAMOS LA BASE DE DATOS DE LOS USUARIOS
    FirebaseFirestore databaseUsuarios;

    ImageView imagenPerfil;
    Button btnCambiarFotoPerfil, btnGuardarFoto;


    private StorageReference storageRef;
    DatabaseReference databaseReference;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Uri imageUri;
    private StorageTask storageTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfil);


        txtId =(TextView) findViewById(R.id.txtPerfilId);
        etPerfilNombre =(TextView) findViewById(R.id.etPerfilNombre);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBarImagenPerfil = findViewById(R.id.progressBarImagenPerfil);
        imagenPerfil = findViewById(R.id.imagenPerfil);
        btnCambiarFotoPerfil = findViewById(R.id.btnCambiarFotoPerfil);
        btnGuardarFoto = findViewById(R.id.btnGuardarFoto);



        txtId.setVisibility(View.GONE);//INICIALMENTE SETEO AMBOS EN GONE PARA DAR TIEMPO
        etPerfilNombre.setVisibility(View.GONE); //A QUE SE CARGUEN DESDE FIREBASE

        //INSTANCIAMOS FIREBASESTORAGE PARA ALMACENAR LAS FOTOS
        storageRef = FirebaseStorage.getInstance().getReference("fotos_perfil");
        //INSTANCIAMOS LA BD DE LOS USUARIOS EN FIRESTORE
        databaseUsuarios = FirebaseFirestore.getInstance();
        //INSTANCIAMOS LA BD DATA PARA LAS FOTOS DE PERFIL EN FIREBASE
        databaseReference = FirebaseDatabase.getInstance().getReference("fotos_perfil");
        //INSTANCIAMOS LA BD DE AUTHENTICATION (ES LA BD DONDE ESTAN LOS DATOS DEL EMAIL)
        fAuth = FirebaseAuth.getInstance();

        //ACA OBTENEMOS EL ID DEL USUARIO DE LA BD AUTHENTICATION
        userID = fAuth.getCurrentUser().getUid();
        txtId.setText("ID "+userID);


        //PIDO PERMISOS PARA QUE ME DEJE LEER Y SACAR FOTOS DEL DISPOSITIVO
        if(!checkPermission()){
            if (ActivityCompat.shouldShowRequestPermissionRationale(Perfil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)  &&
                    ActivityCompat.shouldShowRequestPermissionRationale(Perfil.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(Perfil.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(Perfil.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        }



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
                Toast.makeText(Perfil.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        btnCambiarFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSelectorDeImagen();
            }
        });

        btnGuardarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //con storageTask voy a verificar si no se esta ejecutando una subida a la base de datos por parte del usuario, para que no suba la misma imagen varias veces
                if(storageTask!=null &&storageTask.isInProgress()){
                    Toast.makeText(Perfil.this, "Imagen de perfil Subiendose", Toast.LENGTH_LONG).show();
                }else{subirFoto(userID);}

            }
        });


    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Perfil.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(Perfil.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    public String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void subirFoto(final String userID){
        if(imageUri != null){
            final StorageReference fileReference = storageRef.child(userID+"."+getFileExtension(imageUri));

            storageTask=fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //con el handler le doy un delay de 1 seg a la app, para que cuando la imagen se cargue y la barra se cargue al 100%, no se ponga en 0% tan rapido
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBarImagenPerfil.setProgress(0);
                                        }
                                    },1000);

                                    Toast.makeText(Perfil.this, "Imagen de perfil actualizada", Toast.LENGTH_LONG).show();
                                    //creamos un objeto "foto" para subirlo a la base de datos para asi poder usarlo
                                    UploadFotoPerfil uploadFotoPerfil = new UploadFotoPerfil("foto_"+userID, uri.toString() );
                                    //el objeto foto va a ser child del userId, y es uno solo, por lo que si un usuario desea subir otra foto, sobreescribira esta.
                                    databaseReference.child(userID).setValue(uploadFotoPerfil);
                                }
                            });



                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Perfil.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            //en funcion de los bytes de la imagen ya cargados con el total de la foto, va incrementado el valor progress para que la barra de progreso se cargue
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBarImagenPerfil.setProgress((int)progress);
                        }
                    });
        }else{
            Toast.makeText(this,"No elegiste ninguna foto", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirSelectorDeImagen(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK
        && data!=null &&data.getData()!=null){
            imageUri = data.getData();
            imagenPerfil.setImageURI(imageUri);
        }
    }
}

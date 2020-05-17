package com.devthion.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devthion.myapplication.Administrador.AdminPrincipal;
import com.devthion.myapplication.Administrador.AgregarLocalFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class AlmacenarFoto extends AppCompatActivity {

    StorageReference storageRef;
    DatabaseReference databaseReference;
    final int PICK_IMAGE_REQUEST = 1;
    final int PERMISSION_REQUEST_CODE = 1;
    Uri imageUri;
    StorageTask storageTask;
    Context context;

    public Context context() {
        return context;
    }

    public int getPICK_IMAGE_REQUEST() {
        return PICK_IMAGE_REQUEST;
    }

    public AlmacenarFoto(Context context){
        this.context=context;
    }

   /* public AlmacenarFoto(String unaStorageReference,String unaDatabaseReference,Uri imageUri,StorageTask storageTask){
        this.storageRef= FirebaseStorage.getInstance().getReference(unaStorageReference);
        this.databaseReference = FirebaseDatabase.getInstance().getReference(unaDatabaseReference);
        this.imageUri=imageUri;
        this.storageTask=storageTask;
    }*/

    public void abrirSelectorDeImagen(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity)context).startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }



/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ALMACENAR FOTO", "asd");
        if(requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK
                && data!=null &&data.getData()!=null){
            imageUri = data.getData();
            Log.i("ALMACENAR FOTO", "asd");
            //imagenPerfil.setImageURI(imageUri);
        }
    }*/

}

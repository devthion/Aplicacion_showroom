package com.devthion.myapplication;


import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class DetalleLocal extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalle_local);



        String title = getIntent().getStringExtra("title");



    }
}

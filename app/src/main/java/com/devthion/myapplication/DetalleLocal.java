package com.devthion.myapplication;


import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;



public class DetalleLocal extends AppCompatActivity {



    TextView textVTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalle_local);

        textVTitle =findViewById(R.id.textVTitle);
        String title = getIntent().getStringExtra("title");
        textVTitle.setText(title);

    }
}

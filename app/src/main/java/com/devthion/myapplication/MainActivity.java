package com.devthion.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.devthion.myapplication.ingreso.Registrar;


public class MainActivity extends AppCompatActivity {

    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrar();
            }
        });

    }

    public void openRegistrar(){
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }
}

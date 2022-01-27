package com.example.proyectografos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btn_GrafoDirigido(View view){
        Intent i=new Intent(this,vista_GrafoDirigido.class);
        startActivity(i);

    }

    public void btn_GrafoNoDirigido(View view){
        Intent i=new Intent(this,vista_GrafoNoDirigido.class);
        startActivity(i);

    }

    public void salir_app(View view){
        finish();

    }




}
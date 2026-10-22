package com.example.prfinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.view.WindowCompat;

public class ayuda extends AppCompatActivity {

    ImageButton btnreg1, btni, btnx, btnf;
    Button tercond,PregFrecuentes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        btni = findViewById(R.id.i);
        btni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insta();
            }
        });
        btnf = findViewById(R.id.f);
        btnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                face();
            }
        });
        btnx = findViewById(R.id.x);
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x();
            }
        });
        btnreg1 = findViewById(R.id.reg1);
        btnreg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ayuda.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tercond = findViewById(R.id.tcond);
        tercond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ayuda.this, T_condicion.class);
                startActivity(intent);
                finish();
            }
        });
        PregFrecuentes = findViewById(R.id.Pfrec);
        PregFrecuentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ayuda.this, Pfrecuentes.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void insta() {
        String instagram = "https://www.instagram.com/calcunotasapp/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagram));
        startActivity(intent);
    }

    private void face() {
        String facebook = "https://www.facebook.com/profile.php?id=61560595137636";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebook));
        startActivity(intent);
    }

    private void x() {
        String X = "https://x.com/calcunotas50528";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(X));
        startActivity(intent);
    }
}
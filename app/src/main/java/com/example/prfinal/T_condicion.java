package com.example.prfinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class T_condicion extends AppCompatActivity {

    ImageButton reg11;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcondicion);

        reg11 = findViewById(R.id.reg11);
        reg11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(T_condicion.this, ayuda.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.example.prfinal;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prfinal.BD.BDHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText cod, curs, et3, et4, et5, et6, et7, et8, prom;
    private Cursor fila;
    ListView listaView;
    BDHelper admin;
    SQLiteDatabase db;
    TextView txtpromediogeneral;
    ImageButton btnimage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtpromediogeneral = findViewById(R.id.txtpromm);
        cod = (EditText) findViewById(R.id.edtcodigo);
        curs = (EditText) findViewById(R.id.edtcurso);
        et3 = (EditText) findViewById(R.id.edtnota1);
        et4 = (EditText) findViewById(R.id.edtnota2);
        et5 = (EditText) findViewById(R.id.edtnota3);
        et6 = (EditText) findViewById(R.id.edtnota4);
        et7 = (EditText) findViewById(R.id.edtnota5);
        et8 = (EditText) findViewById(R.id.edtnota6);
        prom = (EditText) findViewById(R.id.edtpromedio);
        listaView = (ListView) findViewById(R.id.lvtablanotas);

        prom.setEnabled(false);

        admin = new BDHelper(this, "instituto", null, 1);

        ArrayList array_list = admin.getAllRegistros();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array_list);

        listaView.setAdapter(arrayAdapter);

        actualizarPromedioGeneral();

        btnimage = findViewById(R.id.ayudabtn);
        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ayuda.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void actualizarPromedioGeneral() {
        double promedioGeneral = admin.getPromedioGeneral();
        txtpromediogeneral.setText("Promedio general: " + promedioGeneral);
    }

    public void Alta(View view) {

        admin = new BDHelper(this, "instituto",
                null, 1);

        db = admin.getWritableDatabase();

        String codigo = cod.getText().toString();
        String curso = curs.getText().toString();
        String nota1 = et3.getText().toString();
        String nota2 = et4.getText().toString();
        String nota3 = et5.getText().toString();
        String nota4 = et6.getText().toString();
        String nota5 = et7.getText().toString();
        String nota6 = et8.getText().toString();

        int promedioope = 0;

        promedioope = Integer.parseInt(et3.getText().toString());
        promedioope = promedioope + Integer.parseInt(et4.getText().toString());
        promedioope = promedioope + Integer.parseInt(et5.getText().toString());
        promedioope = promedioope + Integer.parseInt(et6.getText().toString());
        promedioope = promedioope + Integer.parseInt(et7.getText().toString());
        promedioope = promedioope + Integer.parseInt(et8.getText().toString());

        promedioope = promedioope / 6;

        String promedio = String.valueOf(promedioope);

        ContentValues registro = new ContentValues();
        registro.put("codigo", codigo);
        registro.put("curso", curso);
        registro.put("nota1", nota1);
        registro.put("nota2", nota2);
        registro.put("nota3", nota3);
        registro.put("nota4", nota4);
        registro.put("nota5", nota5);
        registro.put("nota6", nota6);
        registro.put("promedio", promedio);

        db.insert("registro", null, registro);

        db.close();

        ArrayList array_list = admin.getAllRegistros();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, array_list);
        listaView.setAdapter(arrayAdapter);

        cod.setText("");
        curs.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        prom.setText("");

        Toast.makeText(this, "se cargaron los datos ", Toast.LENGTH_SHORT).show();
        actualizarPromedioGeneral();
    }


    public void consulta(View v) {
        admin = new BDHelper(this, "instituto", null, 1);
        db = admin.getWritableDatabase();
        String curso = curs.getText().toString();
        fila = db.rawQuery("select codigo, nota1, nota2, nota3, nota4, nota5, nota6, promedio from registro where curso='" + curso + "'", null);

        if (fila.moveToFirst()) {
            cod.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            et5.setText(fila.getString(3));
            et6.setText(fila.getString(4));
            et7.setText(fila.getString(5));
            et8.setText(fila.getString(6));
            prom.setText(fila.getString(7));
        } else {
            Toast.makeText(this, "No existe un registro para el curso ingresado", Toast.LENGTH_SHORT).show();
        }

        db.close();
        actualizarPromedioGeneral();
    }


    public void baja(View v) {
        admin = new BDHelper(this, "instituto", null, 1);
        db = admin.getWritableDatabase();
        String curso = curs.getText().toString();
        int cant = db.delete("registro", "curso='" + curso + "'", null);
        db.close();

        ArrayList array_list = admin.getAllRegistros();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array_list);
        listaView.setAdapter(arrayAdapter);

        cod.setText("");
        curs.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        prom.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se eliminó el registro para el curso ingresado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe un registro para el curso ingresado", Toast.LENGTH_SHORT).show();
        }
        actualizarPromedioGeneral();
    }

    public void modificacion(View v) {
        admin = new BDHelper(this, "instituto", null, 1);
        db = admin.getWritableDatabase();
        String curso = curs.getText().toString();
        String nota1 = et3.getText().toString();
        String nota2 = et4.getText().toString();
        String nota3 = et5.getText().toString();
        String nota4 = et6.getText().toString();
        String nota5 = et7.getText().toString();
        String nota6 = et8.getText().toString();

        int promedioope = Integer.parseInt(nota1) + Integer.parseInt(nota2) + Integer.parseInt(nota3) + Integer.parseInt(nota4) + Integer.parseInt(nota5) + Integer.parseInt(nota6);
        promedioope = promedioope / 6;
        String promedio = String.valueOf(promedioope);

        ContentValues registro = new ContentValues();
        registro.put("nota1", nota1);
        registro.put("nota2", nota2);
        registro.put("nota3", nota3);
        registro.put("nota4", nota4);
        registro.put("nota5", nota5);
        registro.put("nota6", nota6);
        registro.put("promedio", promedio);

        int cant = db.update("registro", registro, "curso='" + curso + "'", null);
        db.close();

        ArrayList array_list = admin.getAllRegistros();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array_list);
        listaView.setAdapter(arrayAdapter);

        cod.setText("");
        curs.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        prom.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos para el curso ingresado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe un registro para el curso ingresado", Toast.LENGTH_SHORT).show();
        }
        actualizarPromedioGeneral();
    }
}
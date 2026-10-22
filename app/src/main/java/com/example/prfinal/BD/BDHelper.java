package com.example.prfinal.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table registro(codigo integer primary key autoincrement," +
                "curso text not null," +
                "nota1 integer not null," +
                "nota2 integer not null," +
                "nota3 integer not null," +
                "nota4 integer not null," +
                "nota5 integer not null," +
                "nota6 integer not null," +
                "promedio integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists registro");
        onCreate(db);
    }

    public ArrayList<String> getAllRegistros() {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from registro", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            array_list.add("Id:" + res.getString(0) + " | " + "Semestre" + " " +
                    res.getString(1) + " | " + " Promedio " + res.getString(8));
            res.moveToNext();
        }
        res.close();
        db.close();
        return array_list;
    }
    public double getPromedioGeneral() {
        double promedioGeneral = 0;
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT promedio FROM registro", null);
        if (res.moveToFirst()) {
            do {
                promedioGeneral += res.getInt(0);
                count++;
            } while (res.moveToNext());
        }
        res.close();
        db.close();
        if (count > 0) {
            return promedioGeneral / count;
        } else {
            return 0;
        }
    }
}

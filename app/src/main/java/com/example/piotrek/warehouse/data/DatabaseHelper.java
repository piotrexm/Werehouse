package com.example.piotrek.warehouse.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Piotrek on 2017-06-10.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "warehousebase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE articles(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, price FLOAT NOT NULL, id_category INTEGER NOT NULL, id_provider INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE categories(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, id_parent INTEGER);");
        db.execSQL("CREATE TABLE providers(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, tel TEXT NOT NULL, address TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

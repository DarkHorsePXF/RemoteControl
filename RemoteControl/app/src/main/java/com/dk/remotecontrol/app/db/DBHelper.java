package com.dk.remotecontrol.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by feng on 2015/8/8.
 */
public class DBHelper extends SQLiteOpenHelper{
    public static final int DARABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Shutdown.db";

    private final String SQL_CREATE_TABLE_IP = "" +
            "CREATE TABLE connect_ip(" +
            "id INTEGER PRIMARY KEY autoincrement," +
            "ip CHAR(16) UNIQUE" +
            ")";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null, DARABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_IP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

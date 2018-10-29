package com.example.fling.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.jar.Attributes;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String sql = "CREATE TABLE note ( id integer primary key autoincrement,title text not null,context text, "
            + "time TimeStamp NOT NULL DEFAULT (datetime('now','localtime')))";
    private Context mContext;


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(sql);
            Toast.makeText(mContext,"create succeed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists note");
        onCreate(sqLiteDatabase);
    }
}

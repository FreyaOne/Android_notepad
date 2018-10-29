package com.example.fling.note;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AddContext extends AppCompatActivity {

    private EditText editCon;
    private EditText editTit;
    private MyDatabaseHelper dbHelper;
    private String editContext;
    private String editTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);

        editTit =(EditText)findViewById(R.id.editTitle);
        editCon = (EditText)findViewById(R.id.editContext);

        dbHelper = new MyDatabaseHelper(this, "note.db",null,4);


        Button createDataBase = (Button)findViewById(R.id.button2);
        createDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });
        editTit = (EditText)findViewById(R.id.editTitle);
        editCon = (EditText)findViewById(R.id.editContext);

        Button save = (Button)findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                editContext =  editCon.getText().toString();
                editTitle = editTit.getText().toString();
                //开始组装第一条数据
//                Log.e("myTag",editContext);
//                Log.e("myTag",editTitle);
                values.put("context",editContext);
                values.put("title",editTitle);
                db.insert("note",null,values);
                Toast.makeText(AddContext.this,"保存成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddContext.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

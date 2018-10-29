package com.example.fling.note;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private List<Note> data = null;
    private Context context;
    private ListAdapter adapter = null;
    private ListView view;
    private int position = 0;
    private List<Note> dataAll = null;
//    private Note note = new Note();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,AddContext.class);
                startActivity(i);
            }
        });
        context = MainActivity.this;
        view = (ListView)findViewById(R.id.myListView);
        data = new LinkedList<Note>();
        dataAll = new LinkedList<Note>();
        dbHelper = new MyDatabaseHelper(this, "note.db",null,4);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("note",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String i = cursor.getString(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String con = cursor.getString(cursor.getColumnIndex("context"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                data.add(new Note(title,time));
                dataAll.add(new Note(i,title,con,time));
                adapter = new ListAdapter((LinkedList<Note>)data,context);
            }while(cursor.moveToNext());
        }
        cursor.close();

        //        添加并显示
        view.setAdapter(adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//               List<Note> dataAll = new LinkedList<Note>();
                Note note;
               ArrayList<Note> notes = getData();
                note = notes.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id",note.getId());
                bundle.putString("title",note.getTitle());
                bundle.putString("context",note.getContext());
//                    bundle.putString("time",note.getTime());
                Intent intent = new Intent(MainActivity.this,Detail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Note> getData(){
        ArrayList<Note> notes = new ArrayList<>();
        dbHelper = new MyDatabaseHelper(this, "note.db",null,4);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("note",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String i = cursor.getString(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String con = cursor.getString(cursor.getColumnIndex("context"));
//                String time = cursor.getString(cursor.getColumnIndex("time"));
                notes.add(new Note(i,title,con));
//
            }while(cursor.moveToNext());
        }
        cursor.close();
        return notes;
    }
}

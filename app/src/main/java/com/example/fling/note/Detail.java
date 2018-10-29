package com.example.fling.note;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context2);
        Bundle bundle = getIntent().getExtras();
        final String title = bundle.getString("title");
        String con = bundle.getString("context");

        final TextView itemTitle = (TextView) findViewById(R.id.editTit);
        final TextView itemContext = (TextView) findViewById(R.id.editCon);
        itemTitle.setText(title);
        itemContext.setText(con);
        final String id = bundle.getString("id");
        dbHelper = new MyDatabaseHelper(this, "note.db", null, 4);
        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String tit = itemTitle.getText().toString();
                String con = itemContext.getText().toString();
                values.put("title", tit);
                values.put("context", con);
                db.update("note", values, "id = ?", new String[]{id});
                Toast.makeText(Detail.this, "修改成功", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new AlertDialog.Builder(Detail.this)
                        .setTitle("删除确认")
                        .setMessage("\n确定要删除吗")
                        .setPositiveButton("确定", null)
                        .show();
                builder.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete("note", "id=?", new String[]{id});
                        Toast.makeText(Detail.this, "删除成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Detail.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}


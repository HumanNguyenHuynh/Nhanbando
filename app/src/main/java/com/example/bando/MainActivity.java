package com.example.bando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    CursorAdapter myadapter;
    EditText user,pass;
    Button insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list_view);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_LOGIN;
        Cursor c = databaseHelper.getWritableDatabase().rawQuery(query, null);
        // Sử dụng CursorAdapter thay vì ArrayAdapter
        myadapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,c,new String[]{"username", "password"},
                new int[]{android.R.id.text1, android.R.id.text2},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //Set the adapter
        lv.setAdapter(myadapter);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        insert = (Button) findViewById(R.id.insert_button);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("username", username);
                values.put("password", password);
                db.insert(DatabaseHelper.TABLE_LOGIN, null, values);
                db.close();

                String query = "SELECT * FROM " + DatabaseHelper.TABLE_LOGIN;
                // Sử dụng CursorAdapter sử dụng swapCursor để hiển thị dữ liệu mới
                Cursor newCursor = databaseHelper.getWritableDatabase().rawQuery(query, null);
                myadapter.swapCursor(newCursor);
                while (newCursor.moveToNext()) {
                    String user= newCursor.getString(0);
                    String pass = newCursor.getString(1);

                    String data = "user : " + user + " - " + "pass : " + pass;
                    mylist.add(data);
                }

                c.close();
                myadapter.notifyDataSetChanged();
            }
        });
    }

        /*insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("username", username);
                values.put("password", password);
                db.insert(DatabaseHelper.TABLE_LOGIN, null, values);
                db.close();

                mylist.clear();
                String query = "SELECT * FROM bando";
                Cursor c = databaseHelper.getWritableDatabase().rawQuery(query, null);

                while (c.moveToNext()) {
                    String user= c.getString(0);
                    String pass = c.getString(1);

                    String data = "user : " + user + " - " + "pass : " + pass;
                    mylist.add(data);
                }

                c.close();
                myadapter.notifyDataSetChanged();
            }
        });*/

    public void openLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}



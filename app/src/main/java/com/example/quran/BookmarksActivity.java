package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {
    ListView bookMarksListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        bookMarksListView=findViewById(R.id.bookmarksListView);
        DBhelper db=new DBhelper(BookmarksActivity.this,"BookmarksDB.db");

        ArrayList<String> bookmarks=db.listBookmarks();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BookmarksActivity.this, android.R.layout.simple_list_item_1,bookmarks);
        bookMarksListView.setAdapter(arrayAdapter);
    }
}
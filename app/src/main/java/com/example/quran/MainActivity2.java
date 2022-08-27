package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView surahListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        
        surahListView = findViewById(R.id.surahListView);

        Intent intent = getIntent();
        int surahId=intent.getIntExtra("surahId",0);
        DBhelper dbHelper= new DBhelper(MainActivity2.this,"QuranDB.db");
        ArrayList<String> surah= dbHelper.getSurah(surahId);
        myAdapter myAdapter=new myAdapter(this, surah);
        surahListView.setAdapter(myAdapter);

        surahListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int ayahNo=i+1;
                Log.d("======", "ayahNo= "+ayahNo );
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("surahId",surahId);
                intent.putExtra("ayahNo",ayahNo);
                startActivity(intent);
            }
        });
    }
}
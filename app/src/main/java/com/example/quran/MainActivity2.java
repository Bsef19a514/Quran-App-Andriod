package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView surahListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        
        surahListView = findViewById(R.id.surahListView);
        QuranArabicText quranObj = new QuranArabicText();
        String[] quranText=quranObj.QuranArabicText;
        Intent intent = getIntent();
        int startIndex = intent.getIntExtra("startIndex",0);
        int endIndex = intent.getIntExtra("endIndex",0);
         //  Log.d("TAG====", "onCreate: "+startIndex+" "+endIndex);

        ArrayList<String> surah = new ArrayList<String>();
        for (int i = startIndex-1; i < endIndex-1; i++) {
            surah.add(quranText[i]);
        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_list_item_1,surah);
        myAdapter myAdapter=new myAdapter(this, surah);
        surahListView.setAdapter(myAdapter);
    }
}
package com.example.quran;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView surahNamesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surahNamesListView = findViewById(R.id.surahNamesListView);
        QDH obj = new QDH();
//        String[] surahNames=obj.englishSurahNames;
        ArrayList<String> surahNames=new ArrayList<>();
        for(int i=0;i<obj.englishSurahNames.length;i++){
            surahNames.add(i+1+". "+obj.englishSurahNames[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,surahNames);
        surahNamesListView.setAdapter(arrayAdapter);
        surahNamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int surahId=i+1;
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("surahId",surahId);
                startActivity(intent);

            }
        });

    }
}
package com.example.quran;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ListView ayahView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ayahView=findViewById(R.id.ayahView);
        Intent intent = getIntent();
        int surahId=intent.getIntExtra("surahId",0);
        int ayahNo=intent.getIntExtra("ayahNo",0);
        DBhelper dbHelper= new DBhelper(MainActivity3.this,"QuranDB.db");
        ayahModel ayah= dbHelper.translateAyah(surahId,ayahNo);
        ArrayList<ayahModel> ayat=new ArrayList<>();
        ayat.add(ayah);
        ayahAdapter ad=new ayahAdapter(this, ayat);
        ayahView.setAdapter(ad);


        ayahView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ayahModel clickedItem = (ayahModel) ayahView.getItemAtPosition(i);

                //Get the Operation System SDK version as an int
                int sdkVer = android.os.Build.VERSION.SDK_INT;

                //For Older Android SDK versions
                if(sdkVer < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    @SuppressWarnings("deprecation")
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(clickedItem.toString());
                }

                //For Newer Versions
                else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Message",clickedItem.toString());
                    clipboard.setPrimaryClip(clip);
                }
                Toast toast=Toast.makeText(MainActivity3.this,"Text copied to clipboard",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

    }
}
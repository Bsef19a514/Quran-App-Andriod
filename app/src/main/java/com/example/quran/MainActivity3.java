package com.example.quran;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ListView ayahListView;
    ListView surahNamesListView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        int surahId=intent.getIntExtra("surahId",0);
        int ayahNo=intent.getIntExtra("ayahNo",0);
        String surahName=intent.getStringExtra("surahName");
        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);
        String title=surahName+" ("+ayahNo+")";
        toolbar=(Toolbar)findViewById(R.id.tollbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Intent intent;
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        Intent intents = new Intent(MainActivity3.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_para :
                        intents = new Intent(MainActivity3.this, ParaNamesActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_surahs :
                       intents = new Intent(MainActivity3.this, MainActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intents = new Intent(MainActivity3.this, BookmarksActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                }

                return true;
            }
        });

        ayahListView=findViewById(R.id.ayahView);

       DataBaseHelper db=DataBaseHelper.getInstance(MainActivity3.this);
        db.open();
        ayahTranslationModel ayah= db.translateAyah(surahId,ayahNo);
        if(ayah==null){
            Toast toast=Toast.makeText(MainActivity3.this,"Invalid ayah number",Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
        ArrayList<ayahTranslationModel> ayat=new ArrayList<>();
        ayat.add(ayah);
        ayahAdapter ad=new ayahAdapter(this, ayat);
        ayahListView.setAdapter(ad);


        ayahListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ayahTranslationModel clickedItem = (ayahTranslationModel) ayahListView.getItemAtPosition(i);
                //Get the Operation System SDK version as an int
                int sdkVer = android.os.Build.VERSION.SDK_INT;
                //For Older Android SDK versions
                if(sdkVer < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    @SuppressWarnings("deprecation")
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(clickedItem.getAyah()+"\n"+clickedItem.currentT);
                }
                //For Newer Versions
                else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Message",clickedItem.getAyah()+"\n"+clickedItem.currentT);
                    clipboard.setPrimaryClip(clip);
                }
                Toast toast=Toast.makeText(MainActivity3.this,"Text copied to clipboard",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

    }
}
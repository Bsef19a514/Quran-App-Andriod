package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView surahListView;
    ListView surahNamesListView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        int surahId=intent.getIntExtra("surahId",0);
        String surahEnglishName=intent.getStringExtra("surahEnglishName");
        String surahUrduName=intent.getStringExtra("surahUrduName");

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        String title=surahId+". "+surahEnglishName;

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
                        Intent intents = new Intent(MainActivity2.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_para :
                        intents = new Intent(MainActivity2.this, ParaNamesActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_surahs :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intent = new Intent(MainActivity2.this, BookmarksActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                }

                return true;
            }
        });

        surahListView = findViewById(R.id.surahListView);


        DataBaseHelper db=DataBaseHelper.getInstance(getApplicationContext());
        db.open();
        ArrayList<String> surah= db.getSurah(surahId);
        db.close();
//        System.out.println("surah is: "+surah.get(1));
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
                intent.putExtra("surahName",surahEnglishName);
                startActivity(intent);
            }
        });
    }
}
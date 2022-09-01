package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView surahNamesListView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toolbar=(Toolbar)findViewById(R.id.tollbar);
        toolbar.setTitle("Surahs");
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
                        Intent intents = new Intent(MainActivity.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_para :
                        intents = new Intent(MainActivity.this, ParaNamesActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intents = new Intent(MainActivity.this, BookmarksActivity.class);
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

        surahNamesListView = findViewById(R.id.paraNamesListView);
        searchView=findViewById(R.id.surahSearchView);



        QDH obj = new QDH();
//        String englishSurahName;
//        String urduSurahName;
        surahNameModel surahName;
        ArrayList<surahNameModel> surahNameList=new ArrayList<>();
        for(int i=0;i<obj.englishSurahNames.length;i++){
            surahName= new surahNameModel(obj.urduSurahNames[i],obj.englishSurahNames[i],i+1);
            surahNameList.add(surahName);
        }


        surahListAdapter surahNameAdapter = new surahListAdapter(MainActivity.this,surahNameList);
        surahNamesListView.setAdapter(surahNameAdapter);
        surahNamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                surahNameModel sNameModel= (surahNameModel) surahNamesListView.getItemAtPosition(i);
                int surahId=sNameModel.getSurahNo();
                String surahEnglishName=sNameModel.getEnglishName();
                String surahUrduName=sNameModel.getUrduName();
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("surahId",surahId);
                intent.putExtra("surahEnglishName",surahEnglishName);
                intent.putExtra("surahUrduName",surahUrduName);
                startActivity(intent);

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                surahNameAdapter.filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }
}
package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    TextView surahNameTetxView;
    TextView ayahNumberTextView;
    Button searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toolbar=(Toolbar)findViewById(R.id.tollbar);
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
                        Intent intents = new Intent(SearchActivity.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_surahs :
                        intent= new Intent(SearchActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_bookmarks:
                        intent = new Intent(SearchActivity.this, BookmarksActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
        surahNameTetxView=findViewById(R.id.surahName);
        ayahNumberTextView=findViewById(R.id.ayahNumber);
        searchBtn=findViewById(R.id.searchButton);
        QDH obj = new QDH();
        ArrayList<String> surahNames=new ArrayList<>();
        for(int i=0;i<obj.englishSurahNames.length;i++){
            surahNames.add(obj.englishSurahNames[i].trim());
        }
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String surahName = surahNameTetxView.getText().toString().trim();
                    System.out.println("surahName is:"+surahName);
                    String ayahNo=ayahNumberTextView.getText().toString();

                    if(surahName.length()==0 &&  ayahNo.length()==0){
                        Toast toast=Toast.makeText(SearchActivity.this, "Enter Surah name and Ayah no", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CLIP_VERTICAL, 0, 0) ;
                        toast.show();
                        return;
                    }
                    if (ayahNo.length()==0) {
                        int surahNo=surahNames.indexOf(surahName);
                        if(surahNo>-1){
                            surahNo=surahNo++;
                            Intent intent = new Intent(SearchActivity.this,MainActivity2.class);
                            intent.putExtra("surahId",surahNo);
                            intent.putExtra("surahName",surahNo+". "+surahName);
                            startActivity(intent);
                        }
                        Toast toast=Toast.makeText(SearchActivity.this, "No surah found", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CLIP_VERTICAL, 0, 0) ;
                        toast.show();
                        return;
                    }
                    else if(surahName.length()==0){
                        int ayahNumber=Integer.parseInt(ayahNo);
                        DBhelper db = new DBhelper(SearchActivity.this, "QuranDB.db");
                        ayahModel2 ayah=db.getAyah(ayahNumber);
                        if(ayah!=null){
                            int surahId=ayah.getSurahId();
                            int ayahno=ayah.getAyahNo();
                            String surahname=surahNames.get(surahId-1);

                            Intent intent = new Intent(SearchActivity.this,MainActivity3.class);
                            intent.putExtra("surahId",surahId);
                            intent.putExtra("ayahNo",ayahno);
                            intent.putExtra("surahName",surahname+" ("+ayahno+")");
                            startActivity(intent);
                        }else{
                            Toast toast=Toast.makeText(SearchActivity.this, "No ayah found", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CLIP_VERTICAL, 0, 0) ;
                            toast.show();
                            return;
                        }
                    }
                    else {
                        int ayahNumber = Integer.parseInt(ayahNo);
                        DBhelper db = new DBhelper(SearchActivity.this, "QuranDB.db");
                        int surahindex=surahNames.indexOf(surahName);
                        if(surahindex>-1){
                            int suraId=surahindex+1;
                            Intent intent = new Intent(SearchActivity.this,MainActivity3.class);
                            intent.putExtra("surahId",suraId);
                            intent.putExtra("ayahNo",ayahNumber);
                            intent.putExtra("surahName",suraId+". "+surahName);
                            startActivity(intent);
                        }else {
                            Toast toast = Toast.makeText(SearchActivity.this, "Invalid surah name", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CLIP_VERTICAL, 0, 0);
                            toast.show();
                            return;
                        }
                    }
            }
        });
    }
}
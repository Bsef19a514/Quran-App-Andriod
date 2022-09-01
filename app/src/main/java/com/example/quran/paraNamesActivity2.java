package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
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


public class paraNamesActivity2 extends AppCompatActivity {
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
        int paraNo=intent.getIntExtra("paraNo",0);
        String paraEnglishName=intent.getStringExtra("paraEName");
        String paraUrduName=intent.getStringExtra("paraUName");

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        String title=paraNo+". "+paraEnglishName;

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
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        Intent intents = new Intent(paraNamesActivity2.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_para :
                        finish();
                        break;
                    case R.id.nav_surahs :
                        intents = new Intent(paraNamesActivity2.this, MainActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intents = new Intent(paraNamesActivity2.this, BookmarksActivity.class);
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

        surahListView = findViewById(R.id.surahListView);


        DataBaseHelper db=DataBaseHelper.getInstance(getApplicationContext());
        db.open();
        ArrayList<String> para= db.getPara(paraNo);
        db.close();
        myAdapter myAdapter=new myAdapter(this, para);
        surahListView.setAdapter(myAdapter);

        surahListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int ayahNo=i+1;
                String arabicText=myAdapter.getItem(i);
                Intent intent = new Intent(paraNamesActivity2.this,paraActivity3.class);
                intent.putExtra("paraNo",paraNo);
                intent.putExtra("ayahNo",ayahNo);
                intent.putExtra("arabicText",arabicText);
                intent.putExtra("paraName",paraEnglishName);
                startActivity(intent);
            }
        });
    }
}

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

public class BookmarksActivity extends AppCompatActivity {
    ListView bookMarksListView;
    ListView surahNamesListView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toolbar=(Toolbar)findViewById(R.id.tollbar);
        toolbar.setTitle("Bookmarks");
        setSupportActionBar(toolbar);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                Intent intents;
                switch (menuItem.getItemId())
                {

                    case R.id.nav_home:
                        intents = new Intent(BookmarksActivity.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_para :
                        intents = new Intent(BookmarksActivity.this, ParaNamesActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_surahs :
                        intents= new Intent(BookmarksActivity.this, MainActivity.class);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                }

                return true;
            }
        });

        bookMarksListView=findViewById(R.id.bookmarksListView);
        DBhelper db=new DBhelper(BookmarksActivity.this,"BookmarksDB.db");

        ArrayList<ayahTranslationModel> bookmarks=db.listBookMarks();
        bookmarksListAdapter bookmarksAdapter=new bookmarksListAdapter(BookmarksActivity.this,bookmarks);
        bookMarksListView.setAdapter(bookmarksAdapter);

        bookMarksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ayahTranslationModel clickedItem = (ayahTranslationModel) bookMarksListView.getItemAtPosition(i);
                Intent intent = new Intent(BookmarksActivity.this,BookmarksActivity2.class);
                intent.putExtra("ayah",clickedItem.getAyah());
                intent.putExtra("fatehMuhammadJalandhri",clickedItem.getFatehMuhammadJalandhri());
                intent.putExtra("mehmoodUlHassan",clickedItem.getMehmoodUlHassanT());
                intent.putExtra("drMohsinKhan",clickedItem.getDrMohsinKhanT());
                intent.putExtra("muftiTaqiUsmani",clickedItem.getMuftiTaqiUsmaniT());
                intent.putExtra("curretnT",clickedItem.currentT);
                startActivity(intent);
            }
        });
    }
}
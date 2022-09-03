package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity_RV extends AppCompatActivity {
    RecyclerView recyclerView;
    myRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    int surahId;
    String surahEnglishName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rv);

        Intent intent = getIntent();
        surahId=intent.getIntExtra("surahId",0);
        surahEnglishName=intent.getStringExtra("surahEnglishName");
        String surahUrduName=intent.getStringExtra("surahUrduName");

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toolbar=(Toolbar)findViewById(R.id.tollbar);
        String title=surahId+". "+surahEnglishName;
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
                        Intent intents = new Intent(MainActivity_RV.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_para :
                        intents = new Intent(MainActivity_RV.this, ParaNamesActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intents = new Intent(MainActivity_RV.this, BookmarksActivity.class);
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


        recyclerView = findViewById(R.id.surahRecycleView);
        recyclerView.setHasFixedSize(true);

         layoutManager = new LinearLayoutManager(MainActivity_RV.this);
//        layoutManager=new LinearLayoutManager(MainActivity_RV.this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);

        DataBaseHelper db=DataBaseHelper.getInstance(getApplicationContext());
        db.open();
        ArrayList<String> surah= db.getSurah(surahId);
        db.close();

        adapter = new myRecyclerViewAdapter(surah) ;
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(onItemClickListener);




    }
    View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            int ayahNo=position+1;
            Log.d("======", "ayahNo= "+ayahNo );
            Intent intent = new Intent(MainActivity_RV.this,MainActivity3.class);
            intent.putExtra("surahId",surahId);
            intent.putExtra("ayahNo",ayahNo);
            intent.putExtra("surahName",surahEnglishName);
            startActivity(intent);

        }
    };
}
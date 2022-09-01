package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    Button surahsBtn;
    Button bookmarksBtn;
    Button paraBtn;


//    @Override
//    public void onBackPressed(){
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toolbar=(Toolbar)findViewById(R.id.tollbar);

        toolbar.setTitle("The Quran - القرآن");
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
                    case R.id.nav_surahs :
                         intent= new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_para :
                        intent= new Intent(HomeActivity.this, ParaNamesActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_bookmarks:
                        intent = new Intent(HomeActivity.this, BookmarksActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_language:
                        Toast.makeText(getApplicationContext(),"Language is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

        bookmarksBtn=findViewById(R.id.bookmarksBtn);
        surahsBtn=findViewById(R.id.surahBtn);
        paraBtn=findViewById(R.id.paraBtn);

        bookmarksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookmarksActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        paraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ParaNamesActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        surahsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

    }
}
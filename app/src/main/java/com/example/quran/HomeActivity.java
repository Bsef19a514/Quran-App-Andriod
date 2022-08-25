package com.example.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_surahs :
                        Toast.makeText(getApplicationContext(),"Surah is Clicked",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        break;
//
//                    case R.id.nav_return :
//                        Toast.makeText(getApplicationContext(),"Retur is Clicked",Toast.LENGTH_LONG).show();
//                        //drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.nav_laptop :
//                        Toast.makeText(getApplicationContext(),"Laptop is clicked",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
//
//                    case R.id.nav_voice :
//                        Toast.makeText(getApplicationContext(),"Voice is clicked",Toast.LENGTH_LONG).show();
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;

                    case R.id.nav_language:
                        Toast.makeText(getApplicationContext(),"Language is clicked",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
    }
}
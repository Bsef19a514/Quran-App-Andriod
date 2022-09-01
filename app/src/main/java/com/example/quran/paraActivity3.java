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

public class paraActivity3 extends AppCompatActivity {
    ListView ayahView;
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
        int paraNo=intent.getIntExtra("paraNo",0);
        int ayahNo=intent.getIntExtra("ayahNo",0);
        String paraName=intent.getStringExtra("paraName");
        String arabicText=intent.getStringExtra("arabicText");
        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);
        String title=paraName+" ("+ayahNo+")";
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
                        Intent intents = new Intent(paraActivity3.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_surahs :
                        Intent i = new Intent(paraActivity3.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.nav_para :
                        i = new Intent(paraActivity3.this, ParaNamesActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intent = new Intent(paraActivity3.this, BookmarksActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                }

                return true;
            }
        });

        ayahView=findViewById(R.id.ayahView);

       DataBaseHelper db=DataBaseHelper.getInstance(paraActivity3.this);
        db.open();
        ayahTranslationModel ayah= db.translatePara(arabicText);
        if(ayah==null){
            Toast toast=Toast.makeText(paraActivity3.this,"Invalid ayah",Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
        ArrayList<ayahTranslationModel> ayat=new ArrayList<>();
        ayat.add(ayah);
        ayahAdapter ad=new ayahAdapter(this, ayat);
        ayahView.setAdapter(ad);


        ayahView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               ayahTranslationModel clickedItem = (ayahTranslationModel) ayahView.getItemAtPosition(i);

                //Get the Operation System SDK version as an int
                int sdkVer = Build.VERSION.SDK_INT;

                //For Older Android SDK versions
                if(sdkVer < Build.VERSION_CODES.HONEYCOMB) {
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
                Toast toast=Toast.makeText(paraActivity3.this,"Text copied to clipboard",Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

    }
}
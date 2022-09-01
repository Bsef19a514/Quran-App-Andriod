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

public class ParaNamesActivity extends AppCompatActivity {
    ListView paraNamesListView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_names);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);

        toolbar=(Toolbar)findViewById(R.id.tollbar);
        toolbar.setTitle("Paras");
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
                        Intent intents = new Intent(ParaNamesActivity.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_surahs :
                        intents = new Intent(ParaNamesActivity.this, MainActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intents = new Intent(ParaNamesActivity.this, BookmarksActivity.class);
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

        paraNamesListView=findViewById(R.id.paraNamesListView);



        QDH obj = new QDH();


        ParaClass paraModel;
        ArrayList<ParaClass> paraNames=new ArrayList<>();
        for(int i=0;i<obj.englishParahName.length;i++){
            paraModel= new ParaClass();
            paraModel.setParaNo(i+1);
            paraModel.setEnglishPName(obj.englishParahName[i]);
            paraModel.setUrduPName(obj.ParahName[i]);
            paraModel.setParaSIndex(obj.getParahStart(i));
            if(i!=obj.englishParahName.length-1){
                paraModel.setParaEIndex(obj.getParahStart(i+1)-1);
            }else{
                paraModel.setParaEIndex(6236);
            }

            paraNames.add(paraModel);
        }


        paraListAdapter paraNameAdapter = new paraListAdapter(ParaNamesActivity.this,paraNames);
        paraNamesListView.setAdapter(paraNameAdapter);
        paraNamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ParaClass para= (ParaClass) paraNamesListView.getItemAtPosition(i);
                int paraNo=para.getParaNo();
                int paraSIndex=para.getParaSIndex();
                int paraEIndex=para.getParaEIndex();
                String paraEName=para.getEnglishPName();
                String paraUName=para.getUrduPName();

                Intent intent = new Intent(ParaNamesActivity.this,paraNamesActivity2.class);
                intent.putExtra("paraNo",paraNo);
                intent.putExtra("paraSIndex",paraSIndex);
                intent.putExtra("paraEIndex",paraEIndex);
                intent.putExtra("paraEName",paraEName);
                intent.putExtra("paraUName",paraUName);
                startActivity(intent);

            }
        });
    }


}
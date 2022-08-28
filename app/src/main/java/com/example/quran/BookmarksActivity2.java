package com.example.quran;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class BookmarksActivity2 extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    TextView ayahTextView;
    TextView urduTextView;
    TextView englishTextView;
    CheckBox chkBox;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks2);

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
                Intent intent;
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        Intent intents = new Intent(BookmarksActivity2.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_surahs :
                        intent= new Intent(BookmarksActivity2.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        intent = new Intent(BookmarksActivity2.this, BookmarksActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.nav_search:
                        intent = new Intent(BookmarksActivity2.this, SearchActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                }

                return true;
            }
        });

        Intent intent=getIntent();
        String ayah=intent.getStringExtra("ayah");
        String urduT=intent.getStringExtra("urduT");
        String englishT=intent.getStringExtra("englishT");
        ayahModel am=new ayahModel(ayah,urduT,englishT);
        constraintLayout=findViewById(R.id.bookmarkLayout);
        ayahTextView=findViewById(R.id.ayahTextView);
        urduTextView=findViewById(R.id.urduTextView);
        englishTextView=findViewById(R.id.engTextView);
        chkBox=findViewById(R.id.favChkBox);
        Typeface typeface1 = ResourcesCompat.getFont(BookmarksActivity2.this, R.font.noorehuda);
        Typeface typeface2 = ResourcesCompat.getFont(BookmarksActivity2.this, R.font.jameelnoorinastaleeq);
        ayahTextView.setTypeface(typeface1);
        urduTextView.setTypeface(typeface2);
        ayahTextView.setText(ayah);
        urduTextView.setText(urduT);
        englishTextView.setText(englishT);
        chkBox.setChecked(true);

        DBhelper db=new DBhelper(BookmarksActivity2.this,"BookmarksDB.db");
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkBox.isChecked()){
                    int result=db.addBookmark(am);
                    if(result>=1){
                        Toast toast=Toast.makeText(BookmarksActivity2.this,"Added to Bookmarks",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    boolean result=db.deleteBookmark(ayah);
                    if(result){
                        Toast toast=Toast.makeText(BookmarksActivity2.this,"Removed from Bookmarks",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=ayah+"\n"+urduT+"\n"+englishT;
                //Get the Operation System SDK version as an int
                int sdkVer = Build.VERSION.SDK_INT;

                //For Older Android SDK versions
                if(sdkVer < Build.VERSION_CODES.HONEYCOMB) {
                    @SuppressWarnings("deprecation")
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(text);
                }

                //For Newer Versions
                else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Message",text);
                    clipboard.setPrimaryClip(clip);
                }
                Toast toast=Toast.makeText(BookmarksActivity2.this,"Text copied to clipboard",Toast.LENGTH_SHORT);
                toast.show();
//                return false;
            }
        });
    }
}
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
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
    String currentT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks2);

        Spinner changeTranslation=findViewById(R.id.changeTranslation);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(BookmarksActivity2.this, R.array.translations, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        changeTranslation.setAdapter(spinnerAdapter);

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
                        intents = new Intent(BookmarksActivity2.this, HomeActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_para :
                        intents = new Intent(BookmarksActivity2.this, ParaNamesActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_surahs :
                        intents = new Intent(BookmarksActivity2.this, MainActivity.class);
                        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(intents);
                        finish();
                        break;
                    case R.id.nav_bookmarks:
                        finish();
                        break;
                }
                return true;
            }
        });

        Intent intent=getIntent();

        String ayah=intent.getStringExtra("ayah");
        String fatehMuhammadJalandhri=intent.getStringExtra("fatehMuhammadJalandhri");
        String mehmoodUlHassan=intent.getStringExtra("mehmoodUlHassan");
        String drMohsinKhan=intent.getStringExtra("drMohsinKhan");
        String muftiTaqiUsmani=intent.getStringExtra("muftiTaqiUsmani");
        currentT= intent.getStringExtra("curretnT");


//        ayahModel am=new ayahModel(ayah,urduT,englishT);
        ayahTranslationModel ayahObj=new ayahTranslationModel(ayah,fatehMuhammadJalandhri,mehmoodUlHassan,drMohsinKhan,muftiTaqiUsmani);
        ayahObj.currentT=currentT;
        constraintLayout=findViewById(R.id.bookmarkLayout);
        ayahTextView=findViewById(R.id.ayahTextView);
        urduTextView=findViewById(R.id.urduTextView);
        chkBox=findViewById(R.id.favChkBox);
        Typeface typeface1 = ResourcesCompat.getFont(BookmarksActivity2.this, R.font.noorehuda);
        Typeface typeface2 = ResourcesCompat.getFont(BookmarksActivity2.this, R.font.jameelnoorinastaleeq);
        ayahTextView.setTypeface(typeface1);
        urduTextView.setTypeface(typeface2);
        urduTextView.setTextSize(25);
        ayahTextView.setText(ayah);
        urduTextView.setText(currentT);
        chkBox.setChecked(true);

        changeTranslation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharSequence item= spinnerAdapter.getItem(i);
                if(i==0){
                    currentT=fatehMuhammadJalandhri;
                    urduTextView.setText(currentT);
                }else if(i==1){
                    currentT=mehmoodUlHassan;
                    urduTextView.setText(currentT);
                }
                else if(i==2){
                    currentT=drMohsinKhan;
                    urduTextView.setText(currentT);
                    urduTextView.setTextSize(20);

                }else if(i==3){
                    currentT=muftiTaqiUsmani;
                    urduTextView.setText(currentT);
                    urduTextView.setTextSize(20);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                currentT=fatehMuhammadJalandhri;

                urduTextView.setText(currentT);
            }
        });

        DBhelper db=new DBhelper(BookmarksActivity2.this,"BookmarksDB.db");
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkBox.isChecked()){
                    int result=db.addBookmark(ayahObj);
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
        constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String text=ayah+"\n"+currentT;
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
                return false;
            }
        });

    }
}
package com.example.quran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class ayahAdapter extends ArrayAdapter<ayahTranslationModel> {
    public ayahAdapter(@NonNull Context context, ArrayList<ayahTranslationModel> ayah) {
        super(context, 0,ayah);


    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.ayaview,parent,false);

        ayahTranslationModel ayah=getItem(position);
        TextView ayahTextView=convertView.findViewById(R.id.ayahTextView);
        TextView urduTextView=convertView.findViewById(R.id.urduTextView);
        CheckBox favChkBox= convertView.findViewById(R.id.favChkBox);

        Spinner changeTranslation=convertView.findViewById(R.id.changeTranslation);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.translations, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        changeTranslation.setAdapter(spinnerAdapter);

        DBhelper db=new DBhelper(getContext(),"BookmarksDB.db");
        boolean isBookMarked=db.findBookmark(ayah.getAyah());
        if(isBookMarked){
            favChkBox.setChecked(true);
        }
        favChkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favChkBox.isChecked()){
                    int result=db.addBookmark(ayah);
                    if(result>=1){
                        Toast toast=Toast.makeText(getContext(),"Added to Bookmarks",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    boolean result=db.deleteBookmark(ayah.getAyah());
                    if(result){
                        Toast toast=Toast.makeText(getContext(),"Removed from Bookmarks",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });


        Typeface typeface1 = ResourcesCompat.getFont(getContext(), R.font.noorehuda);
        Typeface typeface2 = ResourcesCompat.getFont(getContext(), R.font.jameelnoorinastaleeq);

        ayahTextView.setTypeface(typeface1);
        urduTextView.setTypeface(typeface2);
        urduTextView.setTextSize(25);
//      engTextView.setTypeface(typeface2);
        changeTranslation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CharSequence item= spinnerAdapter.getItem(i);
                if(i==0){
                    ayah.currentT=ayah.getFatehMuhammadJalandhri();
                    urduTextView.setText(ayah.currentT);
                }else if(i==1){
                    ayah.currentT=ayah.getMehmoodUlHassanT();
                    urduTextView.setText(ayah.currentT);
                }
                else if(i==2){
                    ayah.currentT=ayah.getDrMohsinKhanT();
                    urduTextView.setTextSize(20);
                    urduTextView.setText(ayah.currentT);
                }else if(i==3){
                    ayah.currentT=ayah.getMuftiTaqiUsmaniT();
                    urduTextView.setTextSize(20);
                    urduTextView.setText(ayah.currentT);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ayah.currentT=ayah.getFatehMuhammadJalandhri();

                urduTextView.setText(ayah.currentT);
            }
        });
        ayahTextView.setText(ayah.getAyah());
        urduTextView.setText(ayah.currentT);

        return convertView;
    }
}


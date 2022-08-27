package com.example.quran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class ayahAdapter extends ArrayAdapter<ayahModel> {

    public ayahAdapter(@NonNull Context context, ArrayList<ayahModel> ayah) {
        super(context, 0,ayah);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.ayaview,parent,false);

        ayahModel ayah=getItem(position);
        TextView ayahTextView=convertView.findViewById(R.id.ayahTextView);
        TextView urduTextView=convertView.findViewById(R.id.urduTextView);
        TextView engTextView=convertView.findViewById(R.id.engTextView);
        CheckBox favChkBox= convertView.findViewById(R.id.favChkBox);
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
//        engTextView.setTypeface(typeface2);

        ayahTextView.setText(ayah.getAyah());
        urduTextView.setText(ayah.getUrduTranslation());
        engTextView.setText(ayah.getEnglishTranslation());


        return convertView;
    }
}


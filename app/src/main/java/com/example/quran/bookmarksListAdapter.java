package com.example.quran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class bookmarksListAdapter extends ArrayAdapter<ayahTranslationModel> {

    public bookmarksListAdapter(@NonNull Context context, ArrayList<ayahTranslationModel> bookmarks) {
        super(context, 0,bookmarks);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.surahview,parent,false);


        ayahTranslationModel ayat=getItem(position);

        TextView ayatView=convertView.findViewById(R.id.ayatTextView);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.noorehuda);
        ayatView.setTypeface(typeface);
        ayatView.setText(ayat.getAyah());
        return convertView;
    }
}


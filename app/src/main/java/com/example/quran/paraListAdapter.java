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

public class paraListAdapter extends ArrayAdapter<ParaClass> {
    ArrayList<ParaClass> para;
    ArrayList<ParaClass> arraylist;
    public paraListAdapter(@NonNull Context context, ArrayList<ParaClass> para) {
        super(context, 0,para);
        this.para=para;
        arraylist=new ArrayList<>();
        arraylist.addAll(para);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.surahlist_view,parent,false);


        ParaClass para=getItem(position);

        TextView englishName=convertView.findViewById(R.id.englishName);
        TextView urduname=convertView.findViewById(R.id.urduName);
        TextView surahNo=convertView.findViewById(R.id.surahNo);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.noorehuda);
        urduname.setTypeface(typeface);
        urduname.setText(para.getUrduPName());
        englishName.setText(para.getEnglishPName());
//        System.out.println(surah.getSurahNo());
        surahNo.setText(Integer.toString(para.getParaNo()));
        return convertView;
    }

}



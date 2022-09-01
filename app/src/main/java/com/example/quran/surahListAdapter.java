package com.example.quran;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class surahListAdapter extends ArrayAdapter<surahNameModel> {
    ArrayList<surahNameModel> surah;
    ArrayList<surahNameModel> arraylist;
    public surahListAdapter(@NonNull Context context, ArrayList<surahNameModel> surah) {
        super(context, 0,surah);
        this.surah=surah;
        arraylist=new ArrayList<>();
        arraylist.addAll(surah);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.surahlist_view,parent,false);


        surahNameModel surah=getItem(position);

        TextView englishName=convertView.findViewById(R.id.englishName);
        TextView urduname=convertView.findViewById(R.id.urduName);
        TextView surahNo=convertView.findViewById(R.id.surahNo);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.noorehuda);
        urduname.setTypeface(typeface);
        urduname.setText(surah.getUrduName());
        englishName.setText(surah.getEnglishName());
        System.out.println(surah.getSurahNo());
        surahNo.setText(Integer.toString(surah.getSurahNo()));
        return convertView;
    }
    // Filter Class
    public void filter(String charText) {

        surah.clear();
        if (charText.length() == 0) {
            surah.addAll(arraylist);
        } else {
            DataBaseHelper db=DataBaseHelper.getInstance(getContext());
            db.open();
//            DBhelper dbHelper= new DBhelper(getContext(),"QuranDB.db");
            ArrayList<surahNameModel> surahNames=db.getSurahName(charText);
            if(surahNames!=null){
                surahNameModel sModel;
                surahNameModel sModel2;
                for(int i=0;i<surahNames.size();i++){
                    sModel=surahNames.get(i);
                    sModel2=arraylist.get(sModel.getSurahNo()-1);
                    surah.add(sModel2);
                }
            }else{
                Toast toast=Toast.makeText(getContext(), "Invalid Surah name", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CLIP_VERTICAL, 0, 0) ;
                toast.show();
            }
        }
        notifyDataSetChanged();
    }
}



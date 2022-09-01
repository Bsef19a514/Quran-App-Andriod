package com.example.quran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DataBaseHelper instance;
    Cursor c=null;

    private DataBaseHelper(Context context){
        this.openHelper=new AssetHelper(context);
    }
    public static DataBaseHelper getInstance(Context context){
        if(instance==null){
            instance=new DataBaseHelper(context);
        }
        return instance;
    }
    public void open(){
        this.db=openHelper.getWritableDatabase();
    }
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }
    public ArrayList<String> getSurah (int surahId){
            String query = "Select * from tayah where SuraID= " + surahId + " ";
            c = db.rawQuery(query, new String[]{});
            if(c.moveToFirst()){
                ArrayList<String> surah = new ArrayList<>();
                do{
                    surah.add(c.getString(3));
                }while(c.moveToNext());
                return surah;
            }
            return null;
    }

    public ArrayList<String> getPara (int paraId){
        String query;
        query = "Select * from tayah where ParaID= " + paraId + " ";
        c = db.rawQuery(query, new String[]{});
        if (c.moveToFirst()) {
            ArrayList<String> para = new ArrayList<>();
            do {
                para.add(c.getString(3));
            } while (c.moveToNext());
            return para;
        }
        return null;
    }

    public ayahTranslationModel translateAyah(int surahId, int ayaNo){

            String query = "Select * from tayah where SuraID= "+surahId+" and AyaNo= "+ayaNo+" ";
            c = db.rawQuery(query, new String[]{});
        ayahTranslationModel ayah=new ayahTranslationModel();
            if (c.moveToFirst()) {
                ayah.setAyah(c.getString(3));
                ayah.setFatehMuhammadJalandhri(c.getString(4));
                ayah.setMehmoodUlHassanT(c.getString(5));
                ayah.setDrMohsinKhanT(c.getString(6));
                ayah.setMuftiTaqiUsmaniT(c.getString(7));
                c.close();
                ayah.currentT=ayah.getFatehMuhammadJalandhri();
                return ayah;
            }
        return null;
    }
    public ayahTranslationModel translatePara(String arabicText){

        String query = "Select * from tayah where ArabicText= '"+arabicText+"' ";
        c = db.rawQuery(query, new String[]{});
        ayahTranslationModel ayah=new ayahTranslationModel();
        if (c.moveToFirst()) {
            ayah.setAyah(c.getString(3));
            ayah.setFatehMuhammadJalandhri(c.getString(4));
            ayah.setMehmoodUlHassanT(c.getString(5));
            ayah.setDrMohsinKhanT(c.getString(6));
            ayah.setMuftiTaqiUsmaniT(c.getString(7));
            c.close();
            ayah.currentT=ayah.getFatehMuhammadJalandhri();
            return ayah;
        }
        return null;
    }

    public ArrayList<surahNameModel> getSurahName(String name){
            String sql="SELECT * FROM surahNames WHERE SurahNameE LIKE '%"+ name + "%'";

            c= db.rawQuery(sql, new String[]{});
            ArrayList<surahNameModel> surahNames=new ArrayList<>();
            surahNameModel surah;
            if (c.moveToFirst()) {
                do{
                    surah=new surahNameModel();;
                    surah.setSurahNo(c.getInt(0));
                    surahNames.add(surah);
                }while (c.moveToNext());
                c.close();
                return surahNames;
            }
            return null;
    }
}

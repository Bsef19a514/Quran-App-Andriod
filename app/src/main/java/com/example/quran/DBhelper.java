package com.example.quran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
   private String TABLE_NAME="tayah";

    public DBhelper(Context context,String dbName)
    {
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String creteTableQuery="CREATE TABLE Bookmarks (ID Integer PRIMARY KEY AUTOINCREMENT, Ayah text, UrduTranslation text, EnglishTranslation text)";
        database.execSQL(creteTableQuery);
        Log.d("onCreate", "onCreate: table created successfully");
    }
    @Override
    public void onUpgrade(SQLiteDatabase database,int i,int j){

    }
    public ArrayList<String> getSurah (int surahId){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "Select * from " + TABLE_NAME + " where SuraID= " + surahId + " ";
            Cursor cursor = db.rawQuery(query, null);
            ArrayList<String> surah = new ArrayList<>();
            while(cursor.moveToNext()){
                surah.add(cursor.getString(3));
            }
            return surah;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public ayahModel translateAyah(int surahId, int ayaNo){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "Select * from " + TABLE_NAME + " where SuraID= "+surahId+" and AyaNo= "+ayaNo+" ";
            Cursor cursor = db.rawQuery(query, null);
            ayahModel ayah=new ayahModel();
            if (cursor.moveToFirst()) {
                ayah.setAyah(cursor.getString(3));
                ayah.setUrduTranslation(cursor.getString(5));
                ayah.setEnglishTranslation(cursor.getString(6));
                cursor.close();
                return ayah;
            }
        }catch(Exception e){
            Log.d("error", "translateAyah: "+e);
            System.out.println(e);
        }
        return null;
     }


     public int addBookmark (ayahModel ayah){
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();

         cv.put("Ayah", ayah.getAyah());
         cv.put("UrduTranslation", ayah.getUrduTranslation());
         cv.put("EnglishTranslation", ayah.getEnglishTranslation());
         int result = (int) db.insert("Bookmarks", null, cv);
         db.close();
         return result;
     }


    public boolean deleteBookmark(String ayah){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete("Bookmarks","Ayah=?",new String[]{ayah})>0;
    }
    public ArrayList<String> listBookmarks(){
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "Select * from Bookmarks";
            Cursor cursor = db.rawQuery(query, null);
            String ayah;
            ArrayList<String> ayat = new ArrayList<String>();
            if(cursor.moveToLast()){
                do
                {
                    ayah=cursor.getString(1);
                    ayat.add(ayah);
                }while (cursor.moveToPrevious());
            }
            cursor.close();
            return ayat;
    }
    public ArrayList<ayahModel> listBookMarks(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Bookmarks";
        Cursor cursor = db.rawQuery(query, null);
        ayahModel ayah;
        ArrayList<ayahModel> ayat = new ArrayList<>();
        if(cursor.moveToLast()){
            do
            {
                ayah=new ayahModel(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                ayat.add(ayah);
            }while (cursor.moveToPrevious());
        }
        cursor.close();
        return ayat;
    }
    public boolean findBookmark(String ayah){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Bookmarks where Ayah='"+ayah+"'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToNext()){
           return true;
        }
        cursor.close();
        return false;
    }
    public ayahModel2 getAyah(int ayahId){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "Select * from " + TABLE_NAME + " where AyaID= "+ayahId+"";
            Cursor cursor = db.rawQuery(query, null);
            ayahModel2 ayah=new ayahModel2();
            if (cursor.moveToFirst()) {
                ayah.setAyahId(ayahId);
                ayah.setAyahNo(cursor.getInt(2));
                ayah.setSurahId(cursor.getInt(1));
                ayah.setAyah(cursor.getString(3));
                ayah.setUrduTranslation(cursor.getString(5));
                ayah.setEnglishTranslation(cursor.getString(6));
                cursor.close();
                return ayah;
            }
        }catch(Exception e){
            Log.d("error", "translateAyah: "+e);
            System.out.println(e);
        }
        return null;
    }

//    public ayahModel2 getAyah(int surahNmae,int ayahNo){
//        try {
//            SQLiteDatabase db = this.getReadableDatabase();
//            String query = "Select * from " + TABLE_NAME + " where AyaID= "+ayahId+"";
//            Cursor cursor = db.rawQuery(query, null);
//            ayahModel2 ayah=new ayahModel2();
//            if (cursor.moveToFirst()) {
//                ayah.setAyahId(ayahId);
//                ayah.setAyahNo(cursor.getInt(2));
//                ayah.setSurahId(cursor.getInt(1));
//                ayah.setAyah(cursor.getString(3));
//                ayah.setUrduTranslation(cursor.getString(5));
//                ayah.setEnglishTranslation(cursor.getString(6));
//                cursor.close();
//                return ayah;
//            }
//        }catch(Exception e){
//            Log.d("error", "translateAyah: "+e);
//            System.out.println(e);
//        }
//        return null;
//    }
}

package com.example.quran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "tayah";

    public DBhelper(Context context, String dbName) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String creteTableQuery = "CREATE TABLE Bookmarks (ID Integer PRIMARY KEY AUTOINCREMENT, Ayah text, FatehMuhammadJalandhri text, MehmoodUlHassan text, DrMohsinKhan text, MuftiTaqiUsmani text,CurrentT text)";
        database.execSQL(creteTableQuery);

//        creteTableQuery="CREATE TABLE tayah (AyahID Integer , SuraID Integer, AyaNo Integer, ArabicText text, FatehMuhammadJalandri text, DrMohsinKhan text, MuftiTaqiUsmani text, RakuID Integer,PRakuID Integer, ParaID Integer )";
//        database.execSQL(creteTableQuery);
//
//        creteTableQuery="CREATE TABLE surahNames (SurahID Integer , SuraIntro text, SurahNameE text, Nazool text, SurahNameU text)";
//        database.execSQL(creteTableQuery);
        Log.d("onCreate", "onCreate: table created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int j) {

    }

    public int addBookmark(ayahTranslationModel ayah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Ayah", ayah.getAyah());
        cv.put("FatehMuhammadJalandhri", ayah.getFatehMuhammadJalandhri());
        cv.put("MehmoodUlHassan", ayah.getMehmoodUlHassanT());
        cv.put("DrMohsinKhan", ayah.getDrMohsinKhanT());
        cv.put("MuftiTaqiUsmani", ayah.getMuftiTaqiUsmaniT());
        cv.put("CurrentT", ayah.currentT);
        int result = (int) db.insert("Bookmarks", null, cv);
        db.close();
        return result;
    }


    public boolean deleteBookmark(String ayah) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Bookmarks", "Ayah=?", new String[]{ayah}) > 0;
    }

    public ArrayList<ayahTranslationModel> listBookMarks() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Bookmarks";
        Cursor cursor = db.rawQuery(query, null);
        ayahTranslationModel ayah;
        ArrayList<ayahTranslationModel> ayat = new ArrayList<>();
        if (cursor.moveToLast()) {
            do {
                ayah = new ayahTranslationModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                ayah.currentT = cursor.getString(6);
                ayat.add(ayah);
            } while (cursor.moveToPrevious());
        }
        cursor.close();
        return ayat;
    }

    public boolean findBookmark(String ayah) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from Bookmarks where Ayah='" + ayah + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToNext()) {
            return true;
        }
        cursor.close();
        return false;
    }
}

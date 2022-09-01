package com.example.quran;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class AssetHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "QuranDB.db";
    private static final int DATABASE_VERSION = 1;

    public AssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}


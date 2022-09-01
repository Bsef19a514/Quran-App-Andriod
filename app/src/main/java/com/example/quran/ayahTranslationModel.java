package com.example.quran;

public class ayahTranslationModel {
    private String ayah;
    private int surahId;
    private int ayahId;
    private int ayahNo;
    private String fatehMuhammadJalandhri;
    private String mehmoodUlHassanT;
    private String drMohsinKhanT;
    private String muftiTaqiUsmaniT;
    public String currentT;


    public ayahTranslationModel(){
        ayah=null;
        currentT=null;
        fatehMuhammadJalandhri=null;
        mehmoodUlHassanT=null;
        drMohsinKhanT=null;
        muftiTaqiUsmaniT=null;
        ayahId=-1;
        surahId=-1;
        ayahNo=-1;
    }

    public String getAyah() {
        return ayah;
    }

    public void setAyah(String ayah) {
        this.ayah = ayah;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public int getAyahId() {
        return ayahId;
    }

    public void setAyahId(int ayahId) {
        this.ayahId = ayahId;
    }

    public int getAyahNo() {
        return ayahNo;
    }

    public void setAyahNo(int ayahNo) {
        this.ayahNo = ayahNo;
    }

    public String getFatehMuhammadJalandhri() {
        return fatehMuhammadJalandhri;
    }

    public void setFatehMuhammadJalandhri(String fatehMuhammadJalandhri) {
        this.fatehMuhammadJalandhri = fatehMuhammadJalandhri;
    }

    public String getMehmoodUlHassanT() {
        return mehmoodUlHassanT;
    }

    public void setMehmoodUlHassanT(String mehmoodUlHassanT) {
        this.mehmoodUlHassanT = mehmoodUlHassanT;
    }

    public String getDrMohsinKhanT() {
        return drMohsinKhanT;
    }

    public void setDrMohsinKhanT(String drMohsinKhanT) {
        this.drMohsinKhanT = drMohsinKhanT;
    }

    public String getMuftiTaqiUsmaniT() {
        return muftiTaqiUsmaniT;
    }

    public void setMuftiTaqiUsmaniT(String muftiTaqiUsmaniT) {
        this.muftiTaqiUsmaniT = muftiTaqiUsmaniT;
    }

    public ayahTranslationModel(String ayah, String fatehMuhammadJalandhri, String mehmoodUlHassanT, String drMohsinKhanT, String muftiTaqiUsmaniT) {
        this.ayah = ayah;
        this.fatehMuhammadJalandhri = fatehMuhammadJalandhri;
        this.mehmoodUlHassanT = mehmoodUlHassanT;
        this.drMohsinKhanT = drMohsinKhanT;
        this.muftiTaqiUsmaniT = muftiTaqiUsmaniT;
        this.currentT=null;
    }

    public ayahTranslationModel(String ayah, int surahId, int ayahId, int ayahNo, String fatehMuhammadJalandhri, String mehmoodUlHassanT, String drMohsinKhanT, String muftiTaqiUsmaniT) {
        this.ayah = ayah;
        this.surahId = surahId;
        this.ayahId = ayahId;
        this.ayahNo = ayahNo;
        this.fatehMuhammadJalandhri = fatehMuhammadJalandhri;
        this.mehmoodUlHassanT = mehmoodUlHassanT;
        this.drMohsinKhanT = drMohsinKhanT;
        this.muftiTaqiUsmaniT = muftiTaqiUsmaniT;
    }


}

package com.example.quran;

public class ayahModel2 {
    private String ayah;
    private int surahId;
    private int ayahId;
    private int ayahNo;
    private String urduTranslation;
    private String englishTranslation;

    public ayahModel2(){
        ayah=null;
        urduTranslation=null;
        englishTranslation=null;
        ayahId=-1;
        surahId=-1;
        ayahNo=-1;
    }

    public ayahModel2(String ayah, String urduTranslation, String englishTranslation) {
        this.ayah = ayah;
        this.urduTranslation = urduTranslation;
        this.englishTranslation = englishTranslation;
    }

    public String getAyah() {
        return ayah;
    }

    public String getUrduTranslation() {
        return urduTranslation;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }

    public void setAyah(String ayah) {
        this.ayah = ayah;
    }

    public void setUrduTranslation(String urduTranslation) {
        this.urduTranslation = urduTranslation;
    }

    public void setEnglishTranslation(String englishTranslation) {
        this.englishTranslation = englishTranslation;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public void setAyahId(int ayahId) {
        this.ayahId = ayahId;
    }

    public int getSurahId() {
        return surahId;
    }

    public int getAyahId() {
        return ayahId;
    }

    public int getAyahNo() {
        return ayahNo;
    }

    public void setAyahNo(int ayahNo) {
        this.ayahNo = ayahNo;
    }

    @Override
    public String toString(){
        return ayah+"\n"+urduTranslation+"\n"+englishTranslation;
    }
}

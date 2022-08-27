package com.example.quran;

public class ayahModel {
    private String ayah;
    private String urduTranslation;
    private String englishTranslation;

    public ayahModel(){
        ayah=null;
        urduTranslation=null;
        englishTranslation=null;
    }

    public ayahModel(String ayah, String urduTranslation, String englishTranslation) {
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
    @Override
    public String toString(){
        return ayah+"\n"+urduTranslation+"\n"+englishTranslation;
    }
}

package com.example.quran;

public class surahNameModel {
    private String urduName;
    private String englishName;
    private int surahNo;

    public surahNameModel() {
        surahNo=0;
        urduName=null;
        englishName=null;
    }

    public surahNameModel(String urduName, String englishName, int surahNo) {
        this.urduName = urduName;
        this.englishName = englishName;
        this.surahNo = surahNo;
    }

    public String getUrduName() {
        return urduName;
    }

    public void setUrduName(String urduName) {
        this.urduName = urduName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getSurahNo() {
        return surahNo;
    }

    public void setSurahNo(int surahNo) {
        this.surahNo = surahNo;
    }
}

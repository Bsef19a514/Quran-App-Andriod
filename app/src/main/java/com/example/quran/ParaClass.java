package com.example.quran;

public class ParaClass {
    private String englishPName;
    private String urduPName;
    private int paraNo;
    private int paraSIndex;
    private int paraEIndex;

    public String getEnglishPName() {
        return englishPName;
    }

    public void setEnglishPName(String englishPName) {
        this.englishPName = englishPName;
    }

    public String getUrduPName() {
        return urduPName;
    }

    public void setUrduPName(String urduPName) {
        this.urduPName = urduPName;
    }

    public int getParaNo() {
        return paraNo;
    }

    public void setParaNo(int paraNo) {
        this.paraNo = paraNo;
    }

    public int getParaSIndex() {
        return paraSIndex;
    }

    public void setParaSIndex(int paraSIndex) {
        this.paraSIndex = paraSIndex;
    }

    public int getParaEIndex() {
        return paraEIndex;
    }

    public void setParaEIndex(int paraEIndex) {
        this.paraEIndex = paraEIndex;
    }

    public ParaClass() {
        englishPName=null;
        urduPName=null;
        paraNo=-1;
        paraSIndex=-1;
        paraEIndex=-1;
    }

}

package com.itamadersomaj.islamiclifehelper.Model;


public class AllahNameModel {
    int id;
    String meaningEnglish;
    String nameArabic;
    String nameEnglish;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getNameEnglish() {
        return this.nameEnglish;
    }

    public void setNameEnglish(String str) {
        this.nameEnglish = str;
    }

    public String getNameArabic() {
        return this.nameArabic;
    }

    public void setNameArabic(String str) {
        this.nameArabic = str;
    }

    public String getMeaningEnglish() {
        return this.meaningEnglish;
    }

    public void setMeaningEnglish(String str) {
        this.meaningEnglish = str;
    }
}

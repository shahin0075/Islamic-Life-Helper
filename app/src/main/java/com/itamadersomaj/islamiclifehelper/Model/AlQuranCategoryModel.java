package com.itamadersomaj.islamiclifehelper.Model;


public class AlQuranCategoryModel {
    String arabicName;
    String arabicTranslation;
    String englishName;
    int id;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getArabicTranslation() {
        return this.arabicTranslation;
    }

    public void setArabicTranslation(String str) {
        this.arabicTranslation = str;
    }

    public String getArabicName() {
        return this.arabicName;
    }

    public void setArabicName(String str) {
        this.arabicName = str;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName(String str) {
        this.englishName = str;
    }
}

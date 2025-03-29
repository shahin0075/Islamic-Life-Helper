package com.itamadersomaj.islamiclifehelper.Model;


public class AlQuranDataModel {
    String AyahEnglish;
    String ayahArabic;
    String ayahTranslation;
    int id;
    int indexNo;
    boolean isFavorite;

    public int getIndexNo() {
        return this.indexNo;
    }

    public void setIndexNo(int i) {
        this.indexNo = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getAyahArabic() {
        return this.ayahArabic;
    }

    public void setAyahArabic(String str) {
        this.ayahArabic = str;
    }

    public String getAyahTranslation() {
        return this.ayahTranslation;
    }

    public void setAyahTranslation(String str) {
        this.ayahTranslation = str;
    }

    public String getAyahEnglish() {
        return this.AyahEnglish;
    }

    public void setAyahEnglish(String str) {
        this.AyahEnglish = str;
    }

    public boolean isFavorite() {
        return this.isFavorite;
    }

    public void setFavorite(boolean z) {
        this.isFavorite = z;
    }
}

package com.itamadersomaj.islamiclifehelper.Model;


public class DuaModel {
    String dua;
    String enMeaning;
    String enReference;
    int id;
    boolean isFavorite;
    String translation;

    public boolean isFavorite() {
        return this.isFavorite;
    }

    public void setFavorite(boolean z) {
        this.isFavorite = z;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDua() {
        return this.dua;
    }

    public void setDua(String str) {
        this.dua = str;
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String str) {
        this.translation = str;
    }

    public String getEnMeaning() {
        return this.enMeaning;
    }

    public void setEnMeaning(String str) {
        this.enMeaning = str;
    }

    public String getEnReference() {
        return this.enReference;
    }

    public void setEnReference(String str) {
        this.enReference = str;
    }
}

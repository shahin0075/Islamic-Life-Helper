package com.itamadersomaj.islamiclifehelper.Model;


public class AthkarModel {
    String athkarArabic;
    String athkarDescription;
    String athkarEnglish;
    String id;
    int readTime;
    boolean readTimeComplete = false;
    int readTimeCount;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getAthkarArabic() {
        return this.athkarArabic;
    }

    public void setAthkarArabic(String str) {
        this.athkarArabic = str;
    }

    public String getAthkarDescription() {
        return this.athkarDescription;
    }

    public void setAthkarDescription(String str) {
        this.athkarDescription = str;
    }

    public String getAthkarEnglish() {
        return this.athkarEnglish;
    }

    public void setAthkarEnglish(String str) {
        this.athkarEnglish = str;
    }

    public int getReadTime() {
        return this.readTime;
    }

    public void setReadTime(int i) {
        this.readTime = i;
    }

    public boolean isReadTimeComplete() {
        return this.readTimeComplete;
    }

    public void setReadTimeComplete(boolean z) {
        this.readTimeComplete = z;
    }

    public int getReadTimeCount() {
        return this.readTimeCount;
    }

    public void setReadTimeCount(int i) {
        this.readTimeCount = i;
    }
}

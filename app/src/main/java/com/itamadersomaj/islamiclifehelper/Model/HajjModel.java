package com.itamadersomaj.islamiclifehelper.Model;

import java.io.Serializable;


public class HajjModel implements Serializable {
    String architecture;
    String date;
    String day;
    String description;
    String history;
    String imgName;
    String latitude;
    String longitude;
    String subDescription;
    String subTitle;
    String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String str) {
        this.day = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getSubDescription() {
        return this.subDescription;
    }

    public void setSubDescription(String str) {
        this.subDescription = str;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String str) {
        this.history = str;
    }

    public String getArchitecture() {
        return this.architecture;
    }

    public void setArchitecture(String str) {
        this.architecture = str;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String str) {
        this.imgName = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }
}

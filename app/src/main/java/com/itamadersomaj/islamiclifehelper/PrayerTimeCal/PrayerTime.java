package com.itamadersomaj.islamiclifehelper.PrayerTimeCal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;


public class PrayerTime implements GlobalVariables {
    private String InvalidTime;
    private double JDate;
    private double lat;
    private double lng;
    private HashMap<Integer, double[]> methodParams;
    private int numIterations;
    private int[] offsets;
    private double[] prayerTimesCurrent;
    private ArrayList<String> timeNames;
    private double timeZone;
    private int calcMethod = 0;
    private int asrJuristic = 0;
    private int dhuhrMinutes = 0;
    private int adjustHighLats = 1;
    private int timeFormat = 0;

    private double DegreesToRadians(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    private double radiansToDegrees(double d) {
        return (d * 180.0d) / 3.141592653589793d;
    }

    public PrayerTime() {
        setCalcMethod(0);
        setAsrJuristic(0);
        setDhuhrMinutes(0);
        setAdjustHighLats(1);
        setTimeFormat(0);
        ArrayList<String> arrayList = new ArrayList<>();
        this.timeNames = arrayList;
        arrayList.add("Fajr");
        this.timeNames.add("Sunrise");
        this.timeNames.add("Dhuhr");
        this.timeNames.add("Asr");
        this.timeNames.add("Sunset");
        this.timeNames.add("Maghrib");
        this.timeNames.add("Isha");
        this.InvalidTime = "-----";
        setNumIterations(1);
        this.offsets = new int[]{0, 0, 0, 0, 0, 0, 0};
        HashMap<Integer, double[]> hashMap = new HashMap<>();
        this.methodParams = hashMap;
        hashMap.put(0, new double[]{16.0d, 0.0d, 4.0d, 0.0d, 14.0d});
        this.methodParams.put(1, new double[]{18.0d, 1.0d, 0.0d, 0.0d, 18.0d});
        this.methodParams.put(2, new double[]{15.0d, 1.0d, 0.0d, 0.0d, 15.0d});
        this.methodParams.put(3, new double[]{18.0d, 1.0d, 0.0d, 0.0d, 17.0d});
        this.methodParams.put(4, new double[]{18.5d, 1.0d, 0.0d, 1.0d, 90.0d});
        this.methodParams.put(5, new double[]{19.5d, 1.0d, 0.0d, 0.0d, 17.5d});
        this.methodParams.put(7, new double[]{17.7d, 0.0d, 4.5d, 0.0d, 14.0d});
        this.methodParams.put(6, new double[]{18.0d, 1.0d, 0.0d, 0.0d, 17.0d});
    }

    private double fixangle(double d) {
        double floor = d - (Math.floor(d / 360.0d) * 360.0d);
        return floor < 0.0d ? floor + 360.0d : floor;
    }

    private double fixhour(double d) {
        double floor = d - (Math.floor(d / 24.0d) * 24.0d);
        return floor < 0.0d ? floor + 24.0d : floor;
    }

    private double dsin(double d) {
        return Math.sin(DegreesToRadians(d));
    }

    private double dcos(double d) {
        return Math.cos(DegreesToRadians(d));
    }

    private double dtan(double d) {
        return Math.tan(DegreesToRadians(d));
    }

    private double darcsin(double d) {
        return radiansToDegrees(Math.asin(d));
    }

    private double darccos(double d) {
        return radiansToDegrees(Math.acos(d));
    }

    private double darctan(double d) {
        return radiansToDegrees(Math.atan(d));
    }

    private double darctan2(double d, double d2) {
        return radiansToDegrees(Math.atan2(d, d2));
    }

    private double darccot(double d) {
        return radiansToDegrees(Math.atan2(1.0d, d));
    }

    private double getTimeZone1() {
        return (TimeZone.getDefault().getRawOffset() / 1000.0d) / 3600.0d;
    }

    private double getBaseTimeZone() {
        return (TimeZone.getDefault().getRawOffset() / 1000.0d) / 3600.0d;
    }

    private double detectDaylightSaving() {
        return TimeZone.getDefault().getDSTSavings();
    }

    private double julianDate(int i, int i2, int i3) {
        if (i2 <= 2) {
            i--;
            i2 += 12;
        }
        double floor = Math.floor(i / 100.0d);
        return (((Math.floor((i + 4716) * 365.25d) + Math.floor((i2 + 1) * 30.6001d)) + i3) + ((2.0d - floor) + Math.floor(floor / 4.0d))) - 1524.5d;
    }

    private double calcJD(int i, int i2, int i3) {
        return (Math.floor(new Date(i, i2 - 1, i3).getTime() / 8.64E7d) + 2440588.0d) - 0.5d;
    }

    private double[] sunPosition(double d) {
        double d2 = d - 2451545.0d;
        double fixangle = fixangle((0.98560028d * d2) + 357.529d);
        double fixangle2 = fixangle((0.98564736d * d2) + 280.459d);
        double fixangle3 = fixangle((dsin(fixangle) * 1.915d) + fixangle2 + (dsin(fixangle * 2.0d) * 0.02d));
        double d3 = 23.439d - (d2 * 3.6E-7d);
        return new double[]{darcsin(dsin(d3) * dsin(fixangle3)), (fixangle2 / 15.0d) - fixhour(darctan2(dcos(d3) * dsin(fixangle3), dcos(fixangle3)) / 15.0d)};
    }

    private double equationOfTime(double d) {
        return sunPosition(d)[1];
    }

    private double sunDeclination(double d) {
        return sunPosition(d)[0];
    }

    private double computeMidDay(double d) {
        return fixhour(12.0d - equationOfTime(getJDate() + d));
    }

    private double computeTime(double d, double d2) {
        double sunDeclination = sunDeclination(getJDate() + d2);
        double computeMidDay = computeMidDay(d2);
        double darccos = darccos(((-dsin(d)) - (dsin(sunDeclination) * dsin(getLat()))) / (dcos(sunDeclination) * dcos(getLat()))) / 15.0d;
        if (d > 90.0d) {
            darccos = -darccos;
        }
        return computeMidDay + darccos;
    }

    private double computeAsr(double d, double d2) {
        return computeTime(-darccot(d + dtan(Math.abs(getLat() - sunDeclination(getJDate() + d2)))), d2);
    }

    private double timeDiff(double d, double d2) {
        return fixhour(d2 - d);
    }

    private ArrayList<String> getDatePrayerTimes(int i, int i2, int i3, double d, double d2, double d3) {
        setLat(d);
        setLng(d2);
        setTimeZone(d3);
        setJDate(julianDate(i, i2, i3));
        setJDate(getJDate() - (d2 / 360.0d));
        return computeDayTimes();
    }

    public ArrayList<String> getPrayerTimes(Calendar calendar, double d, double d2, double d3) {
        return getDatePrayerTimes(calendar.get(1), calendar.get(2) + 1, calendar.get(5), d, d2, d3);
    }

    private void setCustomParams(double[] dArr) {
        for (int i = 0; i < 5; i++) {
            if (dArr[i] == -1.0d) {
                dArr[i] = this.methodParams.get(Integer.valueOf(getCalcMethod()))[i];
                this.methodParams.put(6, dArr);
            } else {
                this.methodParams.get(6)[i] = dArr[i];
            }
        }
        setCalcMethod(6);
    }

    private void setFajrAngle(double d) {
        setCustomParams(new double[]{d, -1.0d, -1.0d, -1.0d, -1.0d});
    }

    private void setMaghribAngle(double d) {
        setCustomParams(new double[]{-1.0d, 0.0d, d, -1.0d, -1.0d});
    }

    private void setIshaAngle(double d) {
        setCustomParams(new double[]{-1.0d, -1.0d, -1.0d, 0.0d, d});
    }

    private void setMaghribMinutes(double d) {
        setCustomParams(new double[]{-1.0d, 1.0d, d, -1.0d, -1.0d});
    }

    private void setIshaMinutes(double d) {
        setCustomParams(new double[]{-1.0d, -1.0d, -1.0d, 1.0d, d});
    }

    private String floatToTime24(double d) {
        if (Double.isNaN(d)) {
            return this.InvalidTime;
        }
        double fixhour = fixhour(d + 0.008333333333333333d);
        int floor = (int) Math.floor(fixhour);
        double floor2 = Math.floor((fixhour - floor) * 60.0d);
        if (floor >= 0 && floor <= 9 && floor2 >= 0.0d && floor2 <= 9.0d) {
            return "0" + floor + ":0" + Math.round(floor2);
        } else if (floor >= 0 && floor <= 9) {
            return "0" + floor + ":" + Math.round(floor2);
        } else if (floor2 >= 0.0d && floor2 <= 9.0d) {
            return floor + ":0" + Math.round(floor2);
        } else {
            return floor + ":" + Math.round(floor2);
        }
    }

    private String floatToTime12(double d, boolean z) {
        if (Double.isNaN(d)) {
            return this.InvalidTime;
        }
        double fixhour = fixhour(d + 0.008333333333333333d);
        int floor = (int) Math.floor(fixhour);
        double floor2 = Math.floor((fixhour - floor) * 60.0d);
        String str = floor >= 12 ? "PM" : "AM";
        int i = (((floor + 12) - 1) % 12) + 1;
        if (z) {
            if (i >= 0 && i <= 9 && floor2 >= 0.0d && floor2 <= 9.0d) {
                return "0" + i + ":0" + Math.round(floor2);
            } else if (i >= 0 && i <= 9) {
                return "0" + i + ":" + Math.round(floor2);
            } else if (floor2 >= 0.0d && floor2 <= 9.0d) {
                return i + ":0" + Math.round(floor2);
            } else {
                return i + ":" + Math.round(floor2);
            }
        } else if (i >= 0 && i <= 9 && floor2 >= 0.0d && floor2 <= 9.0d) {
            return "0" + i + ":0" + Math.round(floor2) + " " + str;
        } else if (i >= 0 && i <= 9) {
            return "0" + i + ":" + Math.round(floor2) + " " + str;
        } else if (floor2 >= 0.0d && floor2 <= 9.0d) {
            return i + ":0" + Math.round(floor2) + " " + str;
        } else {
            return i + ":" + Math.round(floor2) + " " + str;
        }
    }

    public String floatToTime12NS(double d) {
        return floatToTime12(d, true);
    }

    private double[] computeTimes(double[] dArr) {
        double[] dayPortion = dayPortion(dArr);
        return new double[]{computeTime(180.0d - this.methodParams.get(Integer.valueOf(getCalcMethod()))[0], dayPortion[0]), computeTime(179.167d, dayPortion[1]), computeMidDay(dayPortion[2]), computeAsr(getAsrJuristic() + 1, dayPortion[3]), computeTime(0.833d, dayPortion[4]), computeTime(this.methodParams.get(Integer.valueOf(getCalcMethod()))[2], dayPortion[5]), computeTime(this.methodParams.get(Integer.valueOf(getCalcMethod()))[4], dayPortion[6])};
    }

    private ArrayList<String> computeDayTimes() {
        double[] dArr = {5.0d, 6.0d, 12.0d, 13.0d, 18.0d, 18.0d, 18.0d};
        for (int i = 1; i <= getNumIterations(); i++) {
            dArr = computeTimes(dArr);
        }
        return adjustTimesFormat(tuneTimes(adjustTimes(dArr)));
    }

    private double[] adjustTimes(double[] dArr) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] + (getTimeZone() - (getLng() / 15.0d));
        }
        dArr[2] = dArr[2] + (getDhuhrMinutes() / 60);
        if (this.methodParams.get(Integer.valueOf(getCalcMethod()))[1] == 1.0d) {
            dArr[5] = dArr[4] + (this.methodParams.get(Integer.valueOf(getCalcMethod()))[2] / 60.0d);
        }
        if (this.methodParams.get(Integer.valueOf(getCalcMethod()))[3] == 1.0d) {
            dArr[6] = dArr[5] + (this.methodParams.get(Integer.valueOf(getCalcMethod()))[4] / 60.0d);
        }
        return getAdjustHighLats() != 0 ? adjustHighLatTimes(dArr) : dArr;
    }

    private ArrayList<String> adjustTimesFormat(double[] dArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (getTimeFormat() == 3) {
            for (double d : dArr) {
                arrayList.add(String.valueOf(d));
            }
            return arrayList;
        }
        for (int i = 0; i < 7; i++) {
            if (getTimeFormat() == 1) {
                arrayList.add(floatToTime12(dArr[i], false));
            } else if (getTimeFormat() == 2) {
                arrayList.add(floatToTime12(dArr[i], true));
            } else {
                arrayList.add(floatToTime24(dArr[i]));
            }
        }
        return arrayList;
    }

    private double[] adjustHighLatTimes(double[] dArr) {
        double timeDiff = timeDiff(dArr[4], dArr[1]);
        double nightPortion = nightPortion(this.methodParams.get(Integer.valueOf(getCalcMethod()))[0]) * timeDiff;
        if (Double.isNaN(dArr[0]) || timeDiff(dArr[0], dArr[1]) > nightPortion) {
            dArr[0] = dArr[1] - nightPortion;
        }
        double nightPortion2 = nightPortion(this.methodParams.get(Integer.valueOf(getCalcMethod()))[3] == 0.0d ? this.methodParams.get(Integer.valueOf(getCalcMethod()))[4] : 18.0d) * timeDiff;
        if (Double.isNaN(dArr[6]) || timeDiff(dArr[4], dArr[6]) > nightPortion2) {
            dArr[6] = dArr[4] + nightPortion2;
        }
        double nightPortion3 = nightPortion(this.methodParams.get(Integer.valueOf(getCalcMethod()))[1] == 0.0d ? this.methodParams.get(Integer.valueOf(getCalcMethod()))[2] : 4.0d) * timeDiff;
        if (Double.isNaN(dArr[5]) || timeDiff(dArr[4], dArr[5]) > nightPortion3) {
            dArr[5] = dArr[4] + nightPortion3;
        }
        return dArr;
    }

    private double nightPortion(double d) {
        int i = this.adjustHighLats;
        if (i == 3) {
            return d / 60.0d;
        }
        if (i == 1) {
            return 0.5d;
        }
        return i == 2 ? 0.14286d : 0.0d;
    }

    private double[] dayPortion(double[] dArr) {
        for (int i = 0; i < 7; i++) {
            dArr[i] = dArr[i] / 24.0d;
        }
        return dArr;
    }

    public void setOffsets(int[] iArr) {

        for (int i = 0; i < iArr.length; i++) {
            this.offsets[i] = iArr[i];
        }
    }

    private double[] tuneTimes(double[] dArr) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = dArr[i] + (this.offsets[i] / 60.0d);
        }
        return dArr;
    }

    public int getCalcMethod() {
        return this.calcMethod;
    }

    public void setCalcMethod(int i) {
        this.calcMethod = i;
    }

    public int getAsrJuristic() {
        return this.asrJuristic;
    }

    public void setAsrJuristic(int i) {
        this.asrJuristic = i;
    }

    public int getDhuhrMinutes() {
        return this.dhuhrMinutes;
    }

    public void setDhuhrMinutes(int i) {
        this.dhuhrMinutes = i;
    }

    public int getAdjustHighLats() {
        return this.adjustHighLats;
    }

    public void setAdjustHighLats(int i) {
        this.adjustHighLats = i;
    }

    public int getTimeFormat() {
        return this.timeFormat;
    }

    public void setTimeFormat(int i) {
        this.timeFormat = i;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public double getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(double d) {
        this.timeZone = d;
    }

    public double getJDate() {
        return this.JDate;
    }

    public void setJDate(double d) {
        this.JDate = d;
    }

    public int getNumIterations() {
        return this.numIterations;
    }

    public void setNumIterations(int i) {
        this.numIterations = i;
    }

    public ArrayList<String> getTimeNames() {
        return this.timeNames;
    }
}

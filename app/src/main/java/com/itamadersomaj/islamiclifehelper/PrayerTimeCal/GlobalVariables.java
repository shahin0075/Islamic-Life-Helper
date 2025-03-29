package com.itamadersomaj.islamiclifehelper.PrayerTimeCal;


interface GlobalVariables {

    
    public static final class Adjusting {
        public static final int AngleBased = 3;
        public static final int MidNight = 1;
        public static final int None = 0;
        public static final int OneSeventh = 2;
    }

    
    public static final class Calculation {
        public static final int Custom = 6;
        public static final int Egypt = 5;
        public static final int ISNA = 2;
        public static final int Jafari = 0;
        public static final int Karachi = 1;
        public static final int MWL = 3;
        public static final int Makkah = 4;
        public static final int Tehran = 7;
    }

    
    public static final class Juristic {
        public static final int Hanafi = 1;
        public static final int Shafii = 0;
    }

    
    public static final class Time {
        public static final int Asr = 3;
        public static final int Dhuhr = 2;
        public static final int Fajr = 0;
        public static final int Isha = 6;
        public static final int Maghrib = 5;
        public static final int Sunrise = 1;
        public static final int Sunset = 4;
    }

    
    public static final class TimeFormat {
        public static final int Floating = 3;
        public static final int Time12 = 1;
        public static final int Time12NS = 2;
        public static final int Time24 = 0;
    }
}

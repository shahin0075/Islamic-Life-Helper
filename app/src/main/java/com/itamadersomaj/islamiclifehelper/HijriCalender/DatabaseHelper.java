package com.itamadersomaj.islamiclifehelper.HijriCalender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String COL_1 = "ID";
    public static final String COL_10 = "endyear";
    public static final String COL_11 = "endhour";
    public static final String COL_12 = "endminute";
    public static final String COL_13 = "eventid";
    public static final String COL_14 = "description";
    public static final String COL_2 = "day";
    public static final String COL_3 = "month";
    public static final String COL_4 = "year";
    public static final String COL_5 = "event";
    public static final String COL_6 = "hour";
    public static final String COL_7 = "minute";
    public static final String COL_8 = "endday";
    public static final String COL_9 = "endmonth";
    public static final String DATABASE_NAME = "calendar.db";
    public static final String TABLE_NAME = "reminder_table";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE reminder_table (ID INTEGER PRIMARY KEY AUTOINCREMENT,DAY TEXT,MONTH TEXT,YEAR TEXT,EVENT TEXT,HOUR TEXT,MINUTE TEXT,ENDDAY TEXT,ENDMONTH TEXT,ENDYEAR TEXT,ENDHOUR TEXT,ENDMINUTE TEXT,EVENTID TEXT, DESCRIPTION TEXT )");
    }

    @Override 
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS reminder_table");
        onCreate(sQLiteDatabase);
    }

    public boolean insertData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, str);
        contentValues.put(COL_3, str2);
        contentValues.put(COL_4, str3);
        contentValues.put("event", str4);
        contentValues.put(COL_6, str5);
        contentValues.put(COL_7, str6);
        contentValues.put(COL_8, str7);
        contentValues.put(COL_9, str8);
        contentValues.put(COL_10, str9);
        contentValues.put(COL_11, str10);
        contentValues.put(COL_12, str11);
        contentValues.put(COL_13, str12);
        contentValues.put(COL_14, str13);
        return writableDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public boolean quickinsertdata(List<String> list) {
        int i;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            for (int i2 = 0; i2 < list.size() && (i = i2 * 13) < list.size(); i2++) {
                contentValues.put(COL_2, list.get(i));
                contentValues.put(COL_3, list.get(i + 1));
                contentValues.put(COL_4, list.get(i + 2));
                contentValues.put("event", list.get(i + 3));
                contentValues.put(COL_6, list.get(i + 4));
                contentValues.put(COL_7, list.get(i + 5));
                contentValues.put(COL_8, list.get(i + 6));
                contentValues.put(COL_9, list.get(i + 7));
                contentValues.put(COL_10, list.get(i + 8));
                contentValues.put(COL_11, list.get(i + 9));
                contentValues.put(COL_12, list.get(i + 10));
                contentValues.put(COL_13, list.get(i + 11));
                contentValues.put(COL_14, list.get(i + 12));
                writableDatabase.insert(TABLE_NAME, null, contentValues);
            }
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            return true;
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    public Cursor getAllData() {
        return getWritableDatabase().rawQuery("select * from reminder_table", null);
    }

    public Cursor searchReminder(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.rawQuery("select EVENT from reminder_table where DAY = '" + str + "' AND MONTH = '" + str2 + "' AND YEAR = '" + str3 + "'", null);
    }

    public Cursor searchStartDate(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.rawQuery("select DAY,MONTH,YEAR,HOUR,MINUTE from reminder_table where DAY = '" + str + "' AND MONTH = '" + str2 + "' AND YEAR = '" + str3 + "'", null);
    }

    public Cursor getID_EventID(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.rawQuery("select ID from reminder_table where EVENTID = '" + str + "'", null);
    }

    public Cursor searchEndDate(String str, String str2, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.rawQuery("select ENDDAY,ENDMONTH,ENDYEAR,ENDHOUR,ENDMINUTE from reminder_table where DAY = '" + str + "' AND MONTH = '" + str2 + "' AND YEAR = '" + str3 + "'", null);
    }

    public Cursor searchEventID(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.rawQuery("select EVENTID from reminder_table where DAY = '" + str + "' AND MONTH = '" + str2 + "' AND YEAR = '" + str3 + "' AND EVENT = '" + str4 + "' AND HOUR = '" + str5 + "' AND MINUTE = '" + str6 + "' AND ENDDAY = '" + str7 + "' AND ENDMONTH = '" + str8 + "' AND ENDYEAR = '" + str9 + "' AND ENDHOUR = '" + str10 + "' AND ENDMINUTE = '" + str11 + "'", null);
    }

    public boolean CheckIsDataAlreadyInDBorNot(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("select EVENTID from reminder_table where DAY = '" + str + "' AND MONTH = '" + str2 + "' AND YEAR = '" + str3 + "' AND EVENT = '" + str4 + "' AND HOUR = '" + str5 + "' AND MINUTE = '" + str6 + "' AND ENDDAY = '" + str7 + "' AND ENDMONTH = '" + str8 + "' AND ENDYEAR = '" + str9 + "' AND ENDHOUR = '" + str10 + "' AND ENDMINUTE = '" + str11 + "'", null);
        if (rawQuery.getCount() <= 0) {
            rawQuery.close();
            return false;
        }
        rawQuery.close();
        return true;
    }

    public Cursor searchID(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.rawQuery("select ID from reminder_table where DAY = '" + str + "' AND MONTH = '" + str2 + "' AND YEAR = '" + str3 + "' AND EVENT = '" + str4 + "' AND HOUR = '" + str5 + "' AND MINUTE = '" + str6 + "' AND ENDDAY = '" + str7 + "' AND ENDMONTH = '" + str8 + "' AND ENDYEAR = '" + str9 + "' AND ENDHOUR = '" + str10 + "' AND ENDMINUTE = '" + str11 + "'", null);
    }

    public Cursor getDayData() {
        return getWritableDatabase().rawQuery("select DAY from reminder_table", null);
    }

    public Cursor getMonthData() {
        return getWritableDatabase().rawQuery("select MONTH from reminder_table", null);
    }

    public Cursor getYearData() {
        return getWritableDatabase().rawQuery("select YEAR from reminder_table", null);
    }

    public Cursor getEventData() {
        return getWritableDatabase().rawQuery("select EVENT from reminder_table", null);
    }

    public boolean updateData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, str);
        contentValues.put(COL_2, str2);
        contentValues.put(COL_3, str3);
        contentValues.put(COL_4, str4);
        contentValues.put("event", str5);
        contentValues.put(COL_6, str6);
        contentValues.put(COL_7, str7);
        contentValues.put(COL_8, str8);
        contentValues.put(COL_9, str9);
        contentValues.put(COL_10, str10);
        contentValues.put(COL_11, str11);
        contentValues.put(COL_12, str12);
        contentValues.put(COL_13, str13);
        contentValues.put(COL_14, str14);
        writableDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{str});
        return true;
    }

    public Integer deleteData(String str) {
        return Integer.valueOf(getWritableDatabase().delete(TABLE_NAME, "ID = ?", new String[]{str}));
    }

    public Integer deleteData2(String str, String str2, String str3, String str4) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("select ID from reminder_table where DAY = '" + str2 + "' AND MONTH = '" + str3 + "' AND YEAR = '" + str4 + "'", null);
        if (rawQuery.moveToFirst()) {
            return Integer.valueOf(writableDatabase.delete(TABLE_NAME, "ID = ?", new String[]{rawQuery.getString(rawQuery.getColumnIndex(COL_1))}));
        }
        return Integer.valueOf(writableDatabase.delete(TABLE_NAME, "ID = ?", new String[]{str}));
    }
}

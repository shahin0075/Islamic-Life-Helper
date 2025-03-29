package com.itamadersomaj.islamiclifehelper.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.itamadersomaj.islamiclifehelper.Model.AlQuranCategoryModel;
import com.itamadersomaj.islamiclifehelper.Model.AlQuranDataModel;
import com.itamadersomaj.islamiclifehelper.Model.AllahNameModel;
import com.itamadersomaj.islamiclifehelper.Model.DhikrModel;
import com.itamadersomaj.islamiclifehelper.Model.DuaCategoryModel;
import com.itamadersomaj.islamiclifehelper.Model.DuaModel;
import com.itamadersomaj.islamiclifehelper.Model.DuaTopicModel;
import java.util.ArrayList;
import java.util.List;


public class DatabaseAccess {
    private static DatabaseAccess instance;
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = this.openHelper.getWritableDatabase();
    }

    public void close() {
        SQLiteDatabase sQLiteDatabase = this.database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public List<DuaCategoryModel> getDuaCategory() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM  category", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            DuaCategoryModel duaCategoryModel = new DuaCategoryModel();
            duaCategoryModel.setId(Integer.parseInt(rawQuery.getString(0)));
            duaCategoryModel.setCategoryName(rawQuery.getString(1));
            duaCategoryModel.setImageName(rawQuery.getString(2));
            arrayList.add(duaCategoryModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public List<Integer> getDuaCategoryTopic(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM  topicCategory where categoryID=" + i, null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(Integer.valueOf(rawQuery.getInt(1)));
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public DuaTopicModel getDuaTopic(int i) {
        DuaTopicModel duaTopicModel = new DuaTopicModel();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM  topic where topicID=" + i, null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            duaTopicModel.setId(rawQuery.getInt(0));
            duaTopicModel.setTopicName(rawQuery.getString(1));
            rawQuery.moveToNext();
        }
        return duaTopicModel;
    }

    public List<Integer> getDuaTopicId(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM duaTopic where topicID=" + i, null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(Integer.valueOf(rawQuery.getInt(2)));
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public DuaModel getDua(int i) {
        DuaModel duaModel = new DuaModel();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM dua where duaID=" + i, null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            duaModel.setId(rawQuery.getInt(0));
            duaModel.setDua(rawQuery.getString(1));
            duaModel.setTranslation(rawQuery.getString(2));
            duaModel.setEnMeaning(rawQuery.getString(3));
            duaModel.setEnReference(rawQuery.getString(5));
            try {
                if (rawQuery.getString(8).equals("1")) {
                    duaModel.setFavorite(true);
                } else {
                    duaModel.setFavorite(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                duaModel.setFavorite(false);
            }
            rawQuery.moveToNext();
        }
        return duaModel;
    }

    public DuaModel getRandomDua() {
        DuaModel duaModel = new DuaModel();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM dua ORDER BY RANDOM() LIMIT 1", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            duaModel.setId(rawQuery.getInt(0));
            duaModel.setDua(rawQuery.getString(1));
            duaModel.setTranslation(rawQuery.getString(2));
            duaModel.setEnMeaning(rawQuery.getString(3));
            duaModel.setEnReference(rawQuery.getString(5));
            try {
                if (rawQuery.getString(8).equals("1")) {
                    duaModel.setFavorite(true);
                } else {
                    duaModel.setFavorite(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                duaModel.setFavorite(false);
            }
            rawQuery.moveToNext();
        }
        return duaModel;
    }

    public List<DuaModel> getFavoriteDua() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM dua where is_favroite=1", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            DuaModel duaModel = new DuaModel();
            duaModel.setId(rawQuery.getInt(0));
            duaModel.setDua(rawQuery.getString(1));
            duaModel.setTranslation(rawQuery.getString(2));
            duaModel.setEnMeaning(rawQuery.getString(3));
            duaModel.setEnReference(rawQuery.getString(5));
            try {
                if (rawQuery.getString(8).equals("1")) {
                    duaModel.setFavorite(true);
                } else {
                    duaModel.setFavorite(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                duaModel.setFavorite(false);
            }
            arrayList.add(duaModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public void updateFavoriteDua(DuaModel duaModel) {
        ContentValues contentValues = new ContentValues();
        if (duaModel.isFavorite()) {
            contentValues.put("is_favroite", "1");
        } else {
            contentValues.put("is_favroite", "0");
        }
        this.database.update("dua", contentValues, "duaID=?", new String[]{String.valueOf(duaModel.getId())});
    }

    public List<AllahNameModel> getDua99AllhaName() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM  AllahNames", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            AllahNameModel allahNameModel = new AllahNameModel();
            allahNameModel.setId(Integer.parseInt(rawQuery.getString(0)));
            allahNameModel.setNameEnglish(rawQuery.getString(1));
            allahNameModel.setNameArabic(rawQuery.getString(2));
            allahNameModel.setMeaningEnglish(rawQuery.getString(3));
            arrayList.add(allahNameModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public List<AlQuranCategoryModel> getAlQuranCategory() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM  SuraNames", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            AlQuranCategoryModel alQuranCategoryModel = new AlQuranCategoryModel();
            alQuranCategoryModel.setId(Integer.parseInt(rawQuery.getString(0)));
            alQuranCategoryModel.setArabicName(rawQuery.getString(2));
            alQuranCategoryModel.setArabicTranslation(rawQuery.getString(3));
            alQuranCategoryModel.setEnglishName(rawQuery.getString(4));
            arrayList.add(alQuranCategoryModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public List<AlQuranDataModel> getAlQuranData(int i) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM Quran where SuraID='" + i + "'", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            AlQuranDataModel alQuranDataModel = new AlQuranDataModel();
            alQuranDataModel.setId(Integer.parseInt(rawQuery.getString(0)));
            alQuranDataModel.setIndexNo(Integer.parseInt(rawQuery.getString(2)));
            alQuranDataModel.setAyahArabic(rawQuery.getString(3));
            alQuranDataModel.setAyahTranslation(rawQuery.getString(4));
            alQuranDataModel.setAyahEnglish(rawQuery.getString(5));
            try {
                if (rawQuery.getString(7).equals("1")) {
                    alQuranDataModel.setFavorite(true);
                } else {
                    alQuranDataModel.setFavorite(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                alQuranDataModel.setFavorite(false);
            }
            arrayList.add(alQuranDataModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public List<AlQuranDataModel> getFavoriteQuran() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM Quran where favorite='1'", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            AlQuranDataModel alQuranDataModel = new AlQuranDataModel();
            alQuranDataModel.setId(Integer.parseInt(rawQuery.getString(0)));
            alQuranDataModel.setIndexNo(Integer.parseInt(rawQuery.getString(2)));
            alQuranDataModel.setAyahArabic(rawQuery.getString(3));
            alQuranDataModel.setAyahTranslation(rawQuery.getString(4));
            alQuranDataModel.setAyahEnglish(rawQuery.getString(5));
            try {
                if (rawQuery.getString(7).equals("1")) {
                    alQuranDataModel.setFavorite(true);
                } else {
                    alQuranDataModel.setFavorite(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                alQuranDataModel.setFavorite(false);
            }
            arrayList.add(alQuranDataModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public void updateFavoriteQuran(AlQuranDataModel alQuranDataModel) {
        ContentValues contentValues = new ContentValues();
        if (alQuranDataModel.isFavorite()) {
            contentValues.put("favorite", "1");
        } else {
            contentValues.put("favorite", "0");
        }
        this.database.update("Quran", contentValues, "ID=?", new String[]{String.valueOf(alQuranDataModel.getId())});
    }

    public List<DhikrModel> getAllDhikr() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM Dhikrs", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            DhikrModel dhikrModel = new DhikrModel();
            dhikrModel.setId(Integer.parseInt(rawQuery.getString(0)));
            dhikrModel.setDhikrArabicName(rawQuery.getString(3));
            dhikrModel.setDhikrEnglishName(rawQuery.getString(2));
            dhikrModel.setDhikrEnglishMeaning(rawQuery.getString(4));
            arrayList.add(dhikrModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }

    public DhikrModel getSelectedDhikr(int i) {
        DhikrModel dhikrModel = new DhikrModel();
        Cursor rawQuery = this.database.rawQuery("SELECT * FROM Dhikrs where ID='" + i + "'", null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            dhikrModel.setId(Integer.parseInt(rawQuery.getString(0)));
            dhikrModel.setDhikrArabicName(rawQuery.getString(3));
            dhikrModel.setDhikrEnglishName(rawQuery.getString(2));
            dhikrModel.setDhikrEnglishMeaning(rawQuery.getString(4));
            rawQuery.moveToNext();
        }
        return dhikrModel;
    }
}

package com.itamadersomaj.islamiclifehelper.DbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.itamadersomaj.islamiclifehelper.Model.AthkarModel;
import com.itamadersomaj.islamiclifehelper.Util.Params;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class AthkarDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "athkar.db";
    private static String DB_PATH;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    @Override 
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override 
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public AthkarDatabase(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.context = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    public void createDb() {
        if (!checkDB()) {
            copyDB();
        } else {
            openDB();
        }
    }

    public boolean checkDB() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(DB_PATH);
            sb.append(DB_NAME);
            try {
                return SQLiteDatabase.openDatabase(sb.toString(), null, SQLiteDatabase.OPEN_READONLY) != null;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLiteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void copyDB() {
        try {
            InputStream open = this.context.getAssets().open(DB_NAME);
            String str = DB_PATH + DB_NAME;
            File file = new File(DB_PATH);
            if (!file.exists()) {
                file.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    open.close();
                    openDB();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB() throws SQLException {
        this.sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 0);
    }

    public List<AthkarModel> getAthkarModelList(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = this.sqLiteDatabase.rawQuery("select * from athkar WHERE type =? ", new String[]{str});
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            AthkarModel athkarModel = new AthkarModel();
            athkarModel.setId(rawQuery.getString((int)rawQuery.getColumnIndex(Params.ID2_COL)));
            athkarModel.setAthkarArabic(rawQuery.getString((int)rawQuery.getColumnIndex(Params.ATHKAR_ARABIC_COL)));
            athkarModel.setAthkarDescription(rawQuery.getString((int)rawQuery.getColumnIndex(Params.ATHKAR_DESCRIPTION_COL)));
            athkarModel.setAthkarEnglish(rawQuery.getString((int)rawQuery.getColumnIndex(Params.ATHKAR_ENGLISH_COL)));
            athkarModel.setReadTime(rawQuery.getInt((int)rawQuery.getColumnIndex(Params.ATHKAR_READTIME_COL)));
            arrayList.add(athkarModel);
            rawQuery.moveToNext();
        }
        return arrayList;
    }
}

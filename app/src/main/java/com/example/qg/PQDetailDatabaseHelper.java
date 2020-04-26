package com.example.qg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PQDetailDatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Personal_Questions_table";
    private static final String COL1 = "Quiz_id";
    private static final String COL2 = "Question";
    private static final String COL3 = "Correct";
    private static final String COL4 = "Ia1";
    private static final String COL5 = "Ia2";
    private static final String COL6 = "Ia3";



    public PQDetailDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL1 + " INTEGER ," +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT, " +
                COL5 + " TEXT, " +
                COL6 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete
    }
    public boolean addData(int qId, String q, String c, String ia1, String ia2, String ia3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, qId);
        contentValues.put(COL2, q);
        contentValues.put(COL3, c);
        contentValues.put(COL4, ia1);
        contentValues.put(COL5, ia2);
        contentValues.put(COL6, ia3);
        //Log.d(TAG)
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(int q_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE Quiz_id = "+ Integer.toString(q_id);;
        Cursor data = db.rawQuery(query,null);
        return data;
    }


}


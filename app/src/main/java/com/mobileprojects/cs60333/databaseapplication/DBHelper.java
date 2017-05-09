package com.mobileprojects.cs60333.databaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by bchaudhr on 3/20/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "books.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_BOOK = "Book";
    public static String COL_NAME = "book_name";
    public static String COL_ID = "_id";
    public static String TABLE_IMAGES = "Book_Images";
    public static String COL_IMAGE_ID = "_id";
    public static String COL_IMAGE = "image";
    public static String COL_BOOK_ID = "book_id";
    public static String COL_URI = "uri";
    public static String COL_DATE = "date";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate SQLiteOpenHelper");
        db.execSQL("CREATE TABLE " + TABLE_BOOK + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT )");
        db.execSQL("CREATE TABLE " + TABLE_IMAGES + " ( " + COL_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_BOOK_ID + " INTEGER, " +  COL_IMAGE + " BLOB, " +
                COL_DATE + " TEXT, " + COL_URI + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade SQLiteOpenHelper");
        db.execSQL("DROP TABLE if exists " + TABLE_BOOK );
        db.execSQL("DROP TABLE if exists " + TABLE_IMAGES );
        onCreate(db);
    }

    public void insertData(String tblName, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

        long ret = db.insert(tblName, null, contentValues );

        if (ret > 0) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }

    public void deleteData(String tblName, String clause, int _id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tblName, clause, new String[]{Integer.toString(_id)});
        db.close();
    }


    public Cursor getAllEntries(String tblName, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblName, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getSelectEntries(String tblName, String[] columns, String where, String[] args, String orderBy) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblName, columns, where, args, null, null, orderBy);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getTableFields(String tblName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblName, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

}

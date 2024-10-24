package com.example.persistencetemplate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_project";

    //kalo table banyak jd public staticny bnyk hehe
    public static final String TABLE_STUDENT = "students";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "student_name";
    public static final String FIELD_NIM = "student_nim";

//    constructor dgn parameter context,, siapa yg mangggil and db apa yg dipanggil
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //QUERY UTK CREATE DB
        String query = "CREATE TABLE " + TABLE_STUDENT + "(" + FIELD_ID + " INTEGER PRIMARY KEY," + FIELD_NAME + " TEXT," + FIELD_NIM + " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //ini klo ad perbedaan antara data di hp sm db, misalnya nambah table or wtv
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
        //ini delet table trs data nya ilank jd ga best practice
    }
}

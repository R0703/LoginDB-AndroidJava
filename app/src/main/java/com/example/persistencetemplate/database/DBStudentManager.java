package com.example.persistencetemplate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.persistencetemplate.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class DBStudentManager {
    //panggil dbhelper
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //jadi fileny bs diakses smua halaman
    public DBStudentManager(Context c){
        context = c;
    }

    public DBStudentManager open(){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    //fungsi CRUD
    //CREATE
    public void addStudent(String name, String nim){
        ContentValues values = new ContentValues();
        values.put(DBHelper.FIELD_NAME, name);
        values.put(DBHelper.FIELD_NIM, nim);
        database.insert(DBHelper.TABLE_STUDENT, null, values);
    }

    //READ
    public List<StudentModel> getAllStudent(){
        List<StudentModel> studentList = new ArrayList<>();
        //manual query
        String rawQuery = "SELECT * FROM " + DBHelper.TABLE_STUDENT;

        Cursor cursor = database.rawQuery(rawQuery, null);
        //cursor tuh smacem array

        //mulai cek dr cursor atas, cek ini array bs masukin ke ata 0 ga???
        if(cursor.getCount()> 0){
            if(cursor.moveToFirst()){
                do{
                    int id = Integer.parseInt(cursor.getString(0));
                    //kolom 0 adlh id, 1 name, 2 nim

                    String name = cursor.getString(1);
                    String nim = cursor.getString(2);

                    //simpen dsini
                    StudentModel studentData = new StudentModel(id, name, nim);
                    studentList.add(studentData);
                }while (cursor.moveToNext());
            }
        }

        return studentList;
    }
}

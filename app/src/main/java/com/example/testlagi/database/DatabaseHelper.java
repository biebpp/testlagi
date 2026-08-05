package com.example.testlagi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testlagi.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DbSiswaLocal.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENT = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NIS = "nis";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MAJOR = "major";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NIS + " TEXT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_MAJOR + " TEXT ) ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NIS, student.getNis());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_MAJOR, student.getMajor());

        long id = db.insert(TABLE_STUDENT, null, values);
        db.close();
        return id;
    }

    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();

                student.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                student.setNis(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIS)));
                student.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                student.setMajor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MAJOR)));

                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return studentList;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NIS, student.getNis());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_MAJOR, student.getMajor());

        int rowsAffected = db.update(TABLE_STUDENT, values, COLUMN_ID + " = ?",
                new String[] {String.valueOf(student.getId())});
        return rowsAffected;
    }

    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}

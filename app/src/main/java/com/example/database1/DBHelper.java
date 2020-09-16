package com.example.database1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "student_table";

    private static final String COL_ID = "id";
    private static final String COL_NAME = "student_name";
    private static final String COL_EMAIL = "email";
    private static final String COL_CONTACT = "phone_number";
    private static final String COL_YEAR = "year";
    private static final String COL_COURSE = "course";

    //CREATE TABLE student_table(id INTEGER PRIMARY KEY AUTOINCREMENT,student_name TEXT,email TEXT,phone_number TEXT,year TEXT,course TEXT)

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " TEXT," +
            COL_EMAIL + " TEXT," + COL_CONTACT + " TEXT," + COL_YEAR + " TEXT," + COL_COURSE + " TEXT)";


    public DBHelper(@Nullable Context context) {
        super(context, "studentDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertDataToDatabase(SQLiteDatabase database, StudentDetail studentDetail) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, studentDetail.getStudentName());
        cv.put(COL_EMAIL, studentDetail.getStudentEmail());
        cv.put(COL_CONTACT, studentDetail.getStudentContact());
        cv.put(COL_COURSE, studentDetail.getStudentCourse());
        cv.put(COL_YEAR, studentDetail.getStudentYear());

        database.insert(TABLE_NAME, null, cv);
    }
    public ArrayList<StudentDetail> getDataFromDatabase(SQLiteDatabase database) {
        ArrayList<StudentDetail> studentDetails = new ArrayList<>();


        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
                String contact = cursor.getString(cursor.getColumnIndex(COL_CONTACT));
                String year = cursor.getString(cursor.getColumnIndex(COL_YEAR));
                String course = cursor.getString(cursor.getColumnIndex(COL_COURSE));

                StudentDetail std = new StudentDetail();
                std.setStudentName(name);
                std.setStudentCourse(course);
                std.setStudentEmail(email);
                std.setStudentID(id);
                std.setStudentYear(year);
                std.setStudentContact(contact);

                studentDetails.add(std);
            } while (cursor.moveToNext());
        }

        return studentDetails;
    }

    public void updateDataToDatabase(SQLiteDatabase database, StudentDetail studentDetail) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, studentDetail.getStudentName());
        cv.put(COL_EMAIL, studentDetail.getStudentEmail());
        cv.put(COL_CONTACT, studentDetail.getStudentContact());
        cv.put(COL_COURSE, studentDetail.getStudentCourse());
        cv.put(COL_YEAR, studentDetail.getStudentYear());

        database.update(TABLE_NAME, cv, COL_ID + " = "+ studentDetail.getStudentID(), null);
    }

    public void deleteDataFromDatabase(SQLiteDatabase database, StudentDetail studentDetail){
        database.delete(TABLE_NAME, COL_ID+ " = "+ studentDetail.getStudentID(), null);
    }
}
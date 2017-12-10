package com.example.sasmob.sqlitedatabasedemo.database_module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sasmob on 12/10/2017.
 */

public class DatabaseHelperClass extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentDatabase.db";
    private static final int VERSION_DATABASE = 1;

    private static final String TABLE_NAME_STUDENT = "Student_Informatiom";
    private static final String NAME_STUDENT = "name";
    private static final String STUDENT_DEPARTMENT = "department";
    private static final String STUDENT_ID = "_id";
    private static final String CREATE_TABLE_STUDENT = "create table " + TABLE_NAME_STUDENT + "(" +
            STUDENT_ID + "integer primary key autoincrement, " + NAME_STUDENT +" text not null," + STUDENT_DEPARTMENT+
            "text not null" + ");";


    private static final String TABLE_NAME_FACULTY = "Faculty_Information";
    private static final String NAME_FACULTY = "name";
    private static final String DEPARTMENT_FACULTY = "department";
    private static final String FACULTY_ID = "_id";
    private static final String CREATE_TABLE_FACULTY = "create table " + TABLE_NAME_FACULTY + "(" +
            FACULTY_ID + "integer primary key autoincrement, " + NAME_FACULTY +" text not null," + DEPARTMENT_FACULTY+
            "text not null" + ");";


    private static final String TABLE_NAME_DEPARTMENT = "Department_Information";
    private static final String NAME_DEPARTMENT = "name";
    private static final String DEPARTMENT = "department";
    private static final String DEPARTMENT_ID = "_id";
    private static final String CREATE_TABLE_DEPARTMENT = "create table " + TABLE_NAME_DEPARTMENT + "("
            + DEPARTMENT_ID + "integer primary key autoincrement, " + NAME_DEPARTMENT+" text not null," + DEPARTMENT+
            "text not null" + ");";


    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null ,VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
        //db.execSQL(CREATE_TABLE_FACULTY);
        //db.execSQL(CREATE_TABLE_DEPARTMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_STUDENT);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FACULTY );
        //db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_DEPARTMENT);
        onCreate(db);

    }
    public  long InsertStudentRecord (Student obj1){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_STUDENT, obj1.getName());
        values.put(STUDENT_DEPARTMENT, obj1.getDepartment());
        long l = database.insert(TABLE_NAME_STUDENT, null, values);
        return l;
    }
    public Cursor getAllStudents(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor c= database.rawQuery("select * from " + TABLE_NAME_STUDENT,null);
        return c;
    }
}

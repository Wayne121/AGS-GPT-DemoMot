package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gomesan on 07.06.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION = 1;
    private static  final String DATABASE_NAME = "noteManager";
    private static  final String TABLE_MARK = "Mark";
    private static  final String TABLE_BRANCH = "Branch";
    private static  final String KEY_ID_MARK = "idMark";
    private static  final String KEY_ID_BRANCH = "idBranch";
    private static  final String KEY_NAME_NOTE = "marName";
    private static  final String KEY_TEXT_BRANCH = "braText";
    private static  final String KEY_NOTE_NOTE = "marNote";
    private static  final String KEY_YEAR_NOTE = "marYear";
    private static  final String KEY_YEAR_BRANCH = "braYear";
    private static  final String KEY_IDBRANCH_NOTE = "idBranch";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMarkTable = "CREATE TABLE " + TABLE_MARK + "("
                + KEY_ID_MARK + " INTEGER PRIMARY KEY," + KEY_NAME_NOTE + " TEXT,"
                + KEY_NOTE_NOTE + " TEXT," + KEY_YEAR_NOTE + " TEXT,"  + KEY_IDBRANCH_NOTE + " TEXT" + ")";
        db.execSQL(createMarkTable);

       // String createBranchTable = "CREATE TABLE " + TABLE_BRANCH + "("
       //         + KEY_ID_BRANCH + " INTEGER PRIMARY KEY," + KEY_TEXT_BRANCH + " TEXT,"
       //         + KEY_YEAR_NOTE + " INTEGER" + ")";
       // db.execSQL(createBranchTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRANCH);
        onCreate(db);

        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARK);
        //onCreate(db);
    }


    void addMark(MarkClass markClass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID_MARK, markClass.getIdMark());
        values.put(KEY_NAME_NOTE, markClass.getMarName());
        values.put(KEY_NOTE_NOTE, markClass.getMarNote());
        values.put(KEY_YEAR_NOTE, markClass.getMarYear());
        values.put(KEY_ID_BRANCH, markClass.getIdBranch());

        db.insert(TABLE_MARK, null, values);
        db.close();
    }

    void addBranch(BranchClass branchClass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_MARK, branchClass.getIdBranch());
        values.put(KEY_TEXT_BRANCH, branchClass.getBraText());
        values.put(KEY_YEAR_BRANCH, branchClass.getBraYear());

        db.insert(TABLE_BRANCH, null, values);
        db.close();
    }

    MarkClass getMark(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MARK, new String[] {KEY_ID_MARK, KEY_NAME_NOTE, KEY_NOTE_NOTE, KEY_YEAR_NOTE, KEY_IDBRANCH_NOTE}, KEY_ID_MARK + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        MarkClass markClass = new MarkClass(Integer.parseInt(cursor.getString(0)), cursor.getString(1),  cursor.getString(2),cursor.getString(3), cursor.getString(4));

        return markClass;
    }

    BranchClass getBranch(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BRANCH, new String[] {KEY_ID_MARK, KEY_TEXT_BRANCH, KEY_YEAR_BRANCH}, KEY_ID_BRANCH + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        BranchClass branchClass = new BranchClass(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getInt(2));

        return branchClass;
    }

    public List<MarkClass> getAllMark(String year, String branch) {
        List<MarkClass> markList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_MARK + " WHERE " + KEY_YEAR_NOTE + " = " + year + " AND " + KEY_IDBRANCH_NOTE + " = " + branch;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                MarkClass markClass = new MarkClass();
                markClass.setIdMark(Integer.parseInt(cursor.getString(0)));
                markClass.setMarName(cursor.getString(1));
                markClass.setMarNote(cursor.getString(2));
                markClass.setMarYear(cursor.getString(3));
                markClass.setIdBranch(cursor.getString(4));

                markList.add(markClass);
            }while (cursor.moveToNext());
        }
        return markList;
    }

    public List<BranchClass> getAllBranch() {
        List<BranchClass> branchList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_BRANCH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                BranchClass branchClass = new BranchClass();
                branchClass.setIdBranch(Integer.parseInt(cursor.getString(0)));
                branchClass.setBraText(cursor.getString(1));
                branchClass.setBraYear(cursor.getInt(2));

                branchList.add(branchClass);
            }while (cursor.moveToNext());
        }
        return branchList;
    }

    public void deleteMark(MarkClass markClass){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MARK,KEY_ID_MARK + "=?", new String[] {String.valueOf(markClass.getIdMark())});

        db.close();
    }

    public void deleteBranch(BranchClass branchClass){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BRANCH,KEY_ID_BRANCH + "=?", new String[] {String.valueOf(branchClass.getIdBranch())});

        db.close();
    }

    public int getMarkCount(){
        String countQuery = "SELECT * FROM " + TABLE_MARK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return  cursor.getCount();
    }

    public int getBranchCount(){
        String countQuery = "SELECT * FROM " + TABLE_BRANCH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return  cursor.getCount();
    }
}

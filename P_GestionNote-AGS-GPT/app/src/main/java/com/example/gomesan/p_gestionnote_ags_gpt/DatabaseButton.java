package com.example.gomesan.p_gestionnote_ags_gpt;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gomesan on 09.06.2017.
 */

public class DatabaseButton extends SQLiteOpenHelper  {

    private static  final int DATABASE_VERSION = 1;
    private static  final String DATABASE_NAME = "noteManager";
    private static  final String TABLE_MARK = "Mark";
    private static  final String TABLE_BRANCH = "Branch";
    private static  final String KEY_ID_BRANCH = "idBranch";
    private static  final String KEY_TEXT_BRANCH = "braText";
    private static  final String KEY_YEAR_NOTE = "marYear";
    private static  final String KEY_YEAR_BRANCH = "braYear";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createBranchTable = "CREATE TABLE " + TABLE_BRANCH + "("
                + KEY_ID_BRANCH + " INTEGER PRIMARY KEY," + KEY_TEXT_BRANCH + " TEXT,"
                + KEY_YEAR_NOTE + " TEXT" + ")";
        db.execSQL(createBranchTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARK);
        onCreate(db);
    }

    void addBranch(BranchClass branchClass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_BRANCH, branchClass.getIdBranch());
        values.put(KEY_TEXT_BRANCH, branchClass.getBraText());
        values.put(KEY_YEAR_BRANCH, branchClass.getBraYear());

        db.insert(TABLE_BRANCH, null, values);
        db.close();
    }


    BranchClass getBranch(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BRANCH, new String[] {KEY_ID_MARK, KEY_TEXT_BRANCH, KEY_YEAR_BRANCH}, KEY_ID_BRANCH + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        BranchClass branchClass = new BranchClass(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return branchClass;
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
                branchClass.setBraYear(cursor.getString(2));

                branchList.add(branchClass);
            }while (cursor.moveToNext());
        }
        return branchList;
    }

    public void deleteBranch(BranchClass branchClass){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BRANCH,KEY_ID_BRANCH + "=?", new String[] {String.valueOf(branchClass.getIdBranch())});

        db.close();
    }

    public int getBranchCount(){
        String countQuery = "SELECT * FROM " + TABLE_BRANCH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return  cursor.getCount();
    }
}

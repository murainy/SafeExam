package com.murainy.safeexam.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String CREATE_USER_TABLE = "create table Paper ("
            + "paperId text primary key, "
            + "paperName text, "
            + "joinTime text, "
            + "finishState text)";

    private static final String CREATE_CLASS_TABLE = "create table Grade ("
            + "username text, "
            + "paperName text, "
            + "joinTime text, "
            + "grade int)";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CLASS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Paper");
        db.execSQL("drop table if exists Grade");
        onCreate(db);
    }

}


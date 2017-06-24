package com.murainy.safeexam.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.murainy.safeexam.beans.Grade;
import com.murainy.safeexam.beans.Paper;
import com.murainy.safeexam.beans.Testqeba;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murainy on 2016/1/11.
 */
public class OperateSQLite {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private Context context;


    public OperateSQLite(Context context) {
        this.context = context;
        sqLiteOpenHelper = new SQLiteHelper(context, "StudentTestSystem.db", null, 3);
    }

    public List<Paper> getPaperData() {

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        List<Paper> paperList = new ArrayList<Paper>();
        Cursor cursor = null;
        cursor = db.query("Paper", null, null, null, null, null, "joinTime DESC");


        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                Paper paper;
                do {
                    paper = new Paper();
                    paper.setPaperId(cursor.getString(cursor.getColumnIndex("paperId")));
                    paper.setPaperName(cursor.getString(cursor.getColumnIndex("paperName")));
                    paper.setJoinTime(cursor.getString(cursor.getColumnIndex("joinTime")));
                    if (cursor.getString(cursor.getColumnIndex("finishState")).equals("0")) {
                        paper.setFinishState(false);
                    } else {
                        paper.setFinishState(true);
                    }
                    paperList.add(paper);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return paperList;
    }

    public List<Testqeba> getTestData() {

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        List<Testqeba> paperList = new ArrayList<Testqeba>();
        Cursor cursor = null;
        cursor = db.query("Testqeba", null, null, null, null, null, "id DESC");


        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                Testqeba paper;
                do {
                    paper = new Testqeba();
                    paper.setName(cursor.getString(cursor.getColumnIndex("name")));
                    paper.setNote(cursor.getString(cursor.getColumnIndex("note")));
                    paper.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    if (cursor.getString(cursor.getColumnIndex("finishState")).equals("0")) {
                        paper.setFinishState(false);
                    } else {
                        paper.setFinishState(true);
                    }
                    paperList.add(paper);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return paperList;
    }
    public List<Grade> getGradeData(String username) {

        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        List<Grade> gradeList = new ArrayList<Grade>();
        Cursor cursor = null;
        cursor = db.query("Grade", null, "username = ?", new String[]{username}, null, null, "joinTime DESC");

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                Grade grade;
                do {
                    grade = new Grade();
                    grade.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                    grade.setPaperName(cursor.getString(cursor.getColumnIndex("paperName")));
                    grade.setJoinTime(cursor.getString(cursor.getColumnIndex("joinTime")));
                    grade.setGrade(cursor.getInt(cursor.getColumnIndex("grade")));
                    gradeList.add(grade);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return gradeList;
    }

    public boolean setPaperToDatebase(List<Paper> paper, Context context) {
        for (int i = 0; i <paper.size() ; i++) {

            BmobUtils.updatepaper(paper);
        }

        return true;
    }

    public boolean setGradeToDatebase(Grade grade, Context context) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", grade.getUsername());
        values.put("paperName", grade.getPaperName());
        values.put("joinTime", grade.getJoinTime());
        values.put("grade", grade.getGrade());
        return db.insert("Grade", null, values) >= 0;
    }

}

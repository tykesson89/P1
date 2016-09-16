package com.example.henrik.p1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;

/**
 * Created by Henrik on 2016-09-15.
 */
public class SqLiteDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Mydb";


    public SqLiteDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table income " +
        "(id integer primary key, incomedate date, titel text, category text, amount integer) ");
        db.execSQL("create table expense " +
                "(id integer primary key, expensedate date, titel text, category text, price integer) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS income");
        db.execSQL("DROP TABLE IF EXISTS expense");
        onCreate(db);
    }

    public boolean insertIncome  (String incomeDate, String titel, String category, int amount) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("incomedate", incomeDate);
        contentValues.put("titel", titel);
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        db.insert("income", null, contentValues);
        Log.d("income inserted", " ");
        return true;
    }
    public int getAllIncome(){
        SQLiteDatabase db = this.getReadableDatabase();
        int income = 0;
        Cursor res =  db.rawQuery( "select amount from income", null );
        if(res.getCount()!=0) {

            while (res.moveToNext()) {
                income += res.getInt(res.getColumnIndex("amount"));
            }

            return income;
        }else {
            return 0;
        }
    }

    public int getAllExpense(){
        SQLiteDatabase db = this.getReadableDatabase();
        int income = 0;
        Cursor res =  db.rawQuery( "select price from expense", null );
        if(res.getCount() != 0) {

            while (res.moveToNext()) {
                income += res.getInt(res.getColumnIndex("price"));
            }

            return income;
        }else {
            return 0;
        }
    }
    public boolean insertExpense  (String expenseDate, String titel, String category, int price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("expensedate", expenseDate);
        contentValues.put("titel", titel);
        contentValues.put("category", category);
        contentValues.put("price", price);
        db.insert("expense", null, contentValues);
        Log.d("expense inserted", " ");
        return true;
    }


}

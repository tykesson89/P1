package com.example.henrik.p1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;

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
        "(id integer primary key,  year integer, month integer, day integer, titel text, category text, amount double) ");
        db.execSQL("create table expense " +
                "(id integer primary key, year integer, month integer, day integer, titel text, category text, price double) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS income");
        db.execSQL("DROP TABLE IF EXISTS expense");
        onCreate(db);
    }

    public boolean insertIncome  (int year,int month,int day, String titel, String category, double amount) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("year", year);
        contentValues.put("month", month);
        contentValues.put("day", day);
        contentValues.put("titel", titel);
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        db.insert("income", null, contentValues);
        Log.d("income inserted", " ");
        return true;
    }
    public double getAllIncome(){
        SQLiteDatabase db = this.getReadableDatabase();
        double income = 0;
        Cursor res =  db.rawQuery( "select amount from income", null );
        if(res.getCount()!=0) {

            while (res.moveToNext()) {
                income += res.getDouble(res.getColumnIndex("amount"));
            }

            return income;
        }else {
            return 0;
        }
    }

    public double getAllExpense(){
        SQLiteDatabase db = this.getReadableDatabase();
        double expense = 0;

        Cursor res =  db.rawQuery( "select price from expense", null );
        if(res.getCount() != 0) {

            while (res.moveToNext()) {
                expense += res.getDouble(res.getColumnIndex("price"));
            }

            return expense;
        }else {
            return 0;
        }
    }
    public boolean insertExpense  (int year,int month,int day, String titel, String category, double price) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("year", year);
        contentValues.put("month", month);
        contentValues.put("day", day);
        contentValues.put("titel", titel);
        contentValues.put("category", category);
        contentValues.put("price", price);
        db.insert("expense", null, contentValues);
        Log.d("expense inserted", " ");
        return true;
    }


    public ArrayList<IncomeObject> getAllIncomeObjects(){
        ArrayList<IncomeObject> incomeList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from income ORDER BY month,day,year DESC", null );


            while (res.moveToNext()) {
                IncomeObject incomeObject = new IncomeObject();
                incomeObject.setAmount(res.getDouble(res.getColumnIndex("amount")));
                incomeObject.setCategory(res.getString(res.getColumnIndex("category")));
                incomeObject.setTitel(res.getString(res.getColumnIndex("titel")));
                incomeObject.setYear(res.getInt(res.getColumnIndex("year")));
                incomeObject.setMonth(res.getInt(res.getColumnIndex("month")));
                incomeObject.setDay(res.getInt(res.getColumnIndex("day")));
                incomeList.add(incomeObject);
                Log.d(incomeObject.toString(),"");
            }

            return incomeList;


    }

    public ArrayList<ExpenseObject> getAllExpenseObjects(){
        ArrayList<ExpenseObject> expenseList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM expense ORDER BY month,day,year DESC", null );


        while (res.moveToNext()) {
            ExpenseObject expenseObject = new ExpenseObject();
            expenseObject.setPrice(res.getDouble(res.getColumnIndex("price")));
            expenseObject.setCategory(res.getString(res.getColumnIndex("category")));
            expenseObject.setYear(res.getInt(res.getColumnIndex("year")));
            expenseObject.setMonth(res.getInt(res.getColumnIndex("month")));
            expenseObject.setDay(res.getInt(res.getColumnIndex("day")));
            expenseObject.setTitel(res.getString(res.getColumnIndex("titel")));
            Log.d(expenseObject.toString(), "hej");
            expenseList.add(expenseObject);

        }

        return expenseList;


    }
}

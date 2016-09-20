package com.example.henrik.p1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.vision.barcode.Barcode;

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
        db.execSQL("create table barcodetable " +
                "(id integer primary key, barcode text, titel text, category text, price double) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS income");
        db.execSQL("DROP TABLE IF EXISTS expense");
        db.execSQL("DROP TABLE IF EXISTS barcodetable");
        onCreate(db);
    }

    public boolean insertIncome  (IncomeObject incomeObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("year", incomeObject.getYear());
        contentValues.put("month", incomeObject.getMonth());
        contentValues.put("day", incomeObject.getDay());
        contentValues.put("titel", incomeObject.getTitel());
        contentValues.put("category", incomeObject.getCategory());
        contentValues.put("amount", incomeObject.getAmount());
        db.insert("income", null, contentValues);
        Log.d("income inserted", " ");
        return true;
    }


    public boolean insertExpense  (ExpenseObject expenseObject) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("year", expenseObject.getYear());
        contentValues.put("month", expenseObject.getMonth());
        contentValues.put("day", expenseObject.getDay());
        contentValues.put("titel", expenseObject.getTitel());
        contentValues.put("category", expenseObject.getCategory());
        contentValues.put("price", expenseObject.getPrice());
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
    public int[] getIncomeExpenseResultFromDB(int month, int year){
        int[] arr = new int[3];
        int income = 0;
        int expense = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String strIncome = "SELECT amount from income where "+month+" = month and "+year+" = year";
        Cursor resIncome =  db.rawQuery(strIncome, null);

        while (resIncome.moveToNext()){
            income += resIncome.getInt(resIncome.getColumnIndex("amount"));
        }
        arr[0]=income;

        String strExpense = "SELECT price from expense where "+month+" = month and "+year+" = year";
        Cursor resExpense =  db.rawQuery(strExpense, null);

        while (resExpense.moveToNext()){
            expense += resExpense.getInt(resExpense.getColumnIndex("price"));
        }
        arr[1]=expense;
        arr[2]=income-expense;


        return arr;
    }

    public boolean checkDBForResult(Barcode barcode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String str = "SELECT barcode from barcodetable where "+barcode.displayValue+" = barcode";
        Cursor res =  db.rawQuery(str , null );
        if(res.getCount() == 0){
            return false;
        }else {
            return true;
        }
    }

    public BarcodeObject getBarcodeFromDB(Barcode barcode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String str = "SELECT * from barcodetable where "+barcode.displayValue+" = barcode";
        Cursor res =  db.rawQuery(str , null );
        res.moveToFirst();
        BarcodeObject barcodeObject = new BarcodeObject(barcode.displayValue, res.getString(res.getColumnIndex("titel")),
                res.getString(res.getColumnIndex("category")), res.getDouble(res.getColumnIndex("price")));

        return barcodeObject;
    }

    public void insertBarcode(BarcodeObject barcodeObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("barcode", barcodeObject.getBarcode());
        contentValues.put("titel", barcodeObject.getTitel());
        contentValues.put("category", barcodeObject.getCategory());
        contentValues.put("price", barcodeObject.getPrice());

        db.insert("barcodetable", null, contentValues);
        Log.d("expense inserted", " ");

    }
}

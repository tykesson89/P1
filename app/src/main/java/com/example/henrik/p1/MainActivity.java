package com.example.henrik.p1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.Toast;
import org.joda.time.LocalDate;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private String tag = "myFragment";
    private ArrayList<ExpenseObject> myExpenseListItems = new ArrayList<>();
    private ArrayList<IncomeObject> myIncomeListItems = new ArrayList<>();
    private SqLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new SqLiteDatabase(this);
        db.getWritableDatabase();
        initialize();
    }


    private void initialize() {
        SharedPreferences prefs = getSharedPreferences("Inlogg", MODE_PRIVATE);
        String restoredText = prefs.getString("Firstname", null);
        if(restoredText != null) {

            FragmentManager fm = getFragmentManager();
            if(fm.findFragmentByTag(tag)==null) {
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(R.id.main_container, new MainFragment(), tag);
                fragmentTransaction.commit();
            }

        }else {

            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.main_container, new FragmentLogin(), tag);
            fragmentTransaction.commit();

        }

    }
    public void swapFrag(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_container,fragment,tag);
        ft.addToBackStack(null);
        ft.commit();

    }
    public String getSharedPreferences(){
        SharedPreferences prefs = getSharedPreferences("Inlogg", MODE_PRIVATE);
        String firstname = prefs.getString("Firstname", null);
        String lastname = prefs.getString("Lastname", null);

        return firstname + " " + lastname;

    }
    public ArrayList<ExpenseObject> getExpenseListFromDB(){
        SqLiteDatabase sqLiteDatabase = new SqLiteDatabase(this);
        myExpenseListItems = sqLiteDatabase.getAllExpenseObjects();
        return myExpenseListItems;
    }

    public ArrayList<ExpenseObject> getSortedExpenseList(String dateFrom, String dateTo){
        ArrayList<ExpenseObject> arrayList = new ArrayList<>();
        LocalDate dateStart = new LocalDate(dateFrom);
        LocalDate dateEnd = new LocalDate(dateTo);

        arrayList.clear();
        for(int i = 0; i < myExpenseListItems.size(); i++){
            LocalDate current = new LocalDate(myExpenseListItems.get(i).getFormatedDate());

            if(current.isAfter(dateStart)&&current.isBefore(dateEnd)||current.isEqual(dateStart)||current.isEqual(dateEnd)){
                arrayList.add(myExpenseListItems.get(i));

            }

        }
        return arrayList;
    }
    public ArrayList<IncomeObject> getIncomeListFromDB(){
        SqLiteDatabase sqLiteDatabase = new SqLiteDatabase(this);
        myIncomeListItems = sqLiteDatabase.getAllIncomeObjects();
        return myIncomeListItems;
    }

    public ArrayList<IncomeObject> getSortedIncomeList(String dateFrom, String dateTo){
        ArrayList<IncomeObject> arrayList = new ArrayList<>();
        LocalDate dateStart = new LocalDate(dateFrom);
        LocalDate dateEnd = new LocalDate(dateTo);

        arrayList.clear();
        for(int i = 0; i < myIncomeListItems.size(); i++){
            LocalDate current = new LocalDate(myIncomeListItems.get(i).getFormatedDate());

            if(current.isAfter(dateStart)&&current.isBefore(dateEnd)||current.isEqual(dateStart)||current.isEqual(dateEnd)){
                arrayList.add(myIncomeListItems.get(i));

            }

        }
        return arrayList;
    }
    public boolean insertIncome(IncomeObject incomeObject){
        db.insertIncome(incomeObject);
        return true;
    }
    public boolean insertExpense(ExpenseObject expenseObject){
        db.insertExpense(expenseObject);
        return true;
    }
    public void setToast(String msg){
        Toast.makeText(this, msg , Toast.LENGTH_LONG);
    }
    public void drawPieChart(Bitmap bmp, int[] colors, float[] slices){
        Canvas canvas = new Canvas(bmp);
        RectF box = new RectF(2, 2,bmp.getWidth()-2 , bmp.getHeight()-2);

        float sum = 0;
        for (float slice : slices) {
            sum += slice;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1f);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        float start = 0;

        for(int i =0; i < slices.length; i++){
            paint.setColor(colors[i]);
            float angle;
            angle = ((360.0f / sum) * slices[i]);
            canvas.drawArc(box, start, angle, true, paint);
            start += angle;
        }
    }

    public int[] getIncomeExpenseResult(String month, int year){
        int[] arr;
        int intMonth = getMounth(month);

        arr = db.getIncomeExpenseResultFromDB(intMonth, year);

        return arr;
    }
    public int getMounth(String str){
        if(str.equals("Januari")){
            return 1;
        }else if(str.equals("Februari")){
            return 2;
        }else if(str.equals("Mars")){
            return 3;
        }else if(str.equals("April")){
            return 4;
        }else if(str.equals("Maj")){
            return 5;
        }else if(str.equals("Juni")){
            return 6;
        }else if(str.equals("Juli")){
            return 7;
        }else if(str.equals("Augusti")){
            return 8;
        }else if(str.equals("September")){
            return 9;
        }else if(str.equals("Oktober")){
            return 10;
        }else if(str.equals("November")){
            return 11;
        }else if(str.equals("December")){
            return 12;
        }
        return 0;
    }
    public void intentToBarcode(){
        Intent intent = new Intent(this, BarcodeReader.class);
        startActivity(intent);
        finish();
    }

}

package com.example.henrik.p1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeReader extends Activity {
    private Fragment fragmentInsertBarcodeExpense;
    private Fragment fragmentInsertNewBarcode;
    private SqLiteDatabase sqLiteDatabase;
    private BarcodeObject barcodeObject;
    private String tag = "myFrag";
    private Barcode barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
        initSystem();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initSystem(){

        FragmentManager fm = getFragmentManager();
        if(fm.findFragmentByTag(tag)==null) {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.main_container, new FragmentScanBarcode(), tag);
            fragmentTransaction.commit();
        }

    }


    public void scanBarcode(){
        Intent intent = new Intent(this, ScanBarcode.class);
        startActivityForResult(intent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    this.barcode = barcode;
                    sqLiteDatabase = new SqLiteDatabase(this);
                    boolean result = sqLiteDatabase.checkDBForResult(barcode);
                    if (result) {
                        barcodeObject = sqLiteDatabase.getBarcodeFromDB(barcode);
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.main_container, fragmentInsertBarcodeExpense = new FragmentInsertBarcodeExpense(), tag);
                        ft.addToBackStack(null);
                        ft.commit();
                    } else {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.main_container, fragmentInsertNewBarcode = new FragmentInsertNewBarcode(), tag);
                        ft.addToBackStack(null);
                        ft.commit();
                    }

                } else {

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean insertExpense(ExpenseObject expenseObject){
        sqLiteDatabase.insertExpense(expenseObject);
        return true;
    }
    public String getBarcode(){
        return barcode.displayValue;
    }
    public BarcodeObject getBarcodeObject(){
        return barcodeObject;
    }


    public void insertBarcode(BarcodeObject barcodeObject) {
        sqLiteDatabase.insertBarcode(barcodeObject);
    }
}

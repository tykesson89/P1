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
    private Fragment fragmentScanBarcode;

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
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(R.id.main_container,fragmentScanBarcode = new FragmentScanBarcode(), "hej");
            fragmentTransaction.commit();

    }


    public void scanBarcode(){
        Intent intent = new Intent(this, ScanBarcode.class);
        startActivityForResult(intent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    ((FragmentScanBarcode)fragmentScanBarcode).setBarcodeResult(barcode.displayValue);

                }else {

                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}

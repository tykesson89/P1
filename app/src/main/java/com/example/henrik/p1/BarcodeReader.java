package com.example.henrik.p1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeReader extends Activity {
        private TextView barcodeResult;
    private Button scanBarcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
        initComponents();
        regListeners();
    }
    private void initComponents(){
        barcodeResult = (TextView)findViewById(R.id.barcode_result);
        scanBarcode = (Button)findViewById(R.id.scan_barcode);
    }

    public void scanBarcode(View v){
        Intent intent = new Intent(this, ScanBarcode.class);
        startActivityForResult(intent, 0);
    }
    public void regListeners(){
        scanBarcode.setOnClickListener(new BarcodeScanClickListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data != null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResult.setText("Streckkod: " + barcode.displayValue);

                }else {
                    barcodeResult.setText("Ingen Streckkod hittad");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class BarcodeScanClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            scanBarcode(v);
        }
    }
}

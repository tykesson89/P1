package com.example.henrik.p1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentScanBarcode extends Fragment {
    private TextView barcodeResult;
    private Button scanBarcode;


    public FragmentScanBarcode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_scan_barcode, container, false);
        initComponents(view);
        regListeners();
        return view;
    }

    private void initComponents(View view){
        barcodeResult = (TextView)view.findViewById(R.id.barcode_result);
        scanBarcode = (Button)view.findViewById(R.id.scan_barcode);
    }
    public void setBarcodeResult(String text){
        barcodeResult.setText(text);
    }

    public void regListeners(){
        scanBarcode.setOnClickListener(new BarcodeScanClickListener());
    }
    private class BarcodeScanClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ((BarcodeReader)getActivity()).scanBarcode();
        }
    }
}

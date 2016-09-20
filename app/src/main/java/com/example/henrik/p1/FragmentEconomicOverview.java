package com.example.henrik.p1;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEconomicOverview extends Fragment {
    private Spinner spinnerYear;
    private Spinner spinnerMonth;
    private ImageView imageViewChart;
    private TextView tvNameOverview;
    private TextView tvIncome;
    private TextView tvExpense;
    private TextView tvResult;
    private TextView tvResultText;




    public FragmentEconomicOverview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_economic_overview, container, false);
        initComponents(view);
        setAdapter();
        setName();
        setListeners();
        return view;
    }

    public void initComponents(View view){
        spinnerYear = (Spinner)view.findViewById(R.id.spinnerYear);
        spinnerMonth = (Spinner)view.findViewById(R.id.spinnerMonth);
        imageViewChart = (ImageView)view.findViewById(R.id.imageViewChart);
        tvNameOverview = (TextView)view.findViewById(R.id.tvNameOverview);
        tvIncome = (TextView)view.findViewById(R.id.tvIncome);
        tvExpense = (TextView)view.findViewById(R.id.tvExpense);
        tvResult = (TextView)view.findViewById(R.id.tvResult);
        tvResultText = (TextView)view.findViewById(R.id.tvResultText);
    }

    public void setName(){
       tvNameOverview.setText(((MainActivity)getActivity()).getSharedPreferences());
    }
    public void setAdapter() {
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(getActivity(),
                R.array.Year, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterMonth = ArrayAdapter.createFromResource(getActivity(),
                R.array.month, android.R.layout.simple_spinner_item);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMonth.setAdapter(adapterMonth);
        spinnerYear.setAdapter(adapterYear);
    }
    public void setGraph(int mIncome, int mExpense ){
        int[] colors = {Color.GREEN, Color.RED};
        float[] slices = {mIncome, mExpense};
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(300, 300, conf);
        ((MainActivity)getActivity()).drawPieChart(bitmap, colors, slices);
        imageViewChart.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
    }
    public void setListeners(){
        spinnerMonth.setOnItemSelectedListener(new SpinnerMonthYearSelectListener());
        spinnerYear.setOnItemSelectedListener(new SpinnerMonthYearSelectListener());
    }


    private class SpinnerMonthYearSelectListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String str =(String)spinnerMonth.getSelectedItem();
            int i = Integer.parseInt((String)spinnerYear.getSelectedItem());
            int[] arr;
            arr = ((MainActivity)getActivity()).getIncomeExpenseResult(str, i);
            setGraph(arr[0], arr[1]);
            setIncomeExpenseText(arr);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void setIncomeExpenseText(int[] arr) {

        tvIncome.setText(String.valueOf(arr[0]));
        tvIncome.setTextColor(Color.GREEN);
        tvExpense.setText(String.valueOf(arr[1]));
        tvExpense.setTextColor(Color.RED);
        tvResult.setText(String.valueOf(arr[2]));
        if(arr[2] < 0){
            tvResultText.setTextColor(Color.RED);
            tvResult.setTextColor(Color.RED);
        }else if(arr[2]> 0){
            tvResultText.setTextColor(Color.GREEN);
            tvResult.setTextColor(Color.GREEN);
        }
    }
}

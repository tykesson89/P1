package com.example.henrik.p1;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInsertBarcodeExpense extends Fragment {
    private EditText etTitel;
    private TextView tvCategory;
    private EditText etPrice;
    private CalendarView calendarView;
    private Button btnInsertExpense;
    private int mYear;
    private int mMonth;
    private int mDay;
    private BarcodeObject barcodeObject;


    public FragmentInsertBarcodeExpense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_insert_barcode_expense, container, false);
        initComponents(view);
        regListeners();
        setText();
        return view;
    }
    private void initComponents(View view){
        etTitel = (EditText)view.findViewById(R.id.etExpenseTitel);
        tvCategory = (TextView) view.findViewById(R.id.tvCategory);
        etPrice = (EditText)view.findViewById(R.id.etPrice);
        calendarView = (CalendarView)view.findViewById(R.id.calendarExpense);
        btnInsertExpense = (Button)view.findViewById(R.id.btnInsertExpense);
    }

    public void setText(){
        barcodeObject = ((BarcodeReader)getActivity()).getBarcodeObject();
        etTitel.setText(barcodeObject.getTitel());
        etPrice.setText(String.valueOf(barcodeObject.getPrice()));
        tvCategory.setText(barcodeObject.getCategory());
    }

   private void regListeners(){
       btnInsertExpense.setOnClickListener(new InsertExpenseListener());
       calendarView.setOnDateChangeListener(new DateChangeListener());
   }

    private class InsertExpenseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ExpenseObject expenseObject = new ExpenseObject();
            expenseObject.setMonth(mMonth);
            expenseObject.setYear(mYear);
            expenseObject.setPrice(Double.parseDouble(etPrice.getText().toString()));
            expenseObject.setDay(mDay);
            expenseObject.setCategory(tvCategory.getText().toString());
            expenseObject.setTitel(etTitel.getText().toString());
            ((BarcodeReader)getActivity()).insertExpense(expenseObject);

        }
    }

    private class DateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = (month+1);
            mDay = dayOfMonth;
        }
    }
}

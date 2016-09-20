package com.example.henrik.p1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInsertNewBarcode extends Fragment {
    private TextView streckkodid;
    private Spinner spinnerExpense;
    private EditText etTitel;
    private EditText etPrice;
    private CalendarView calendarView;
    private Button addBarcode;
    private int mYear;
    private int mMonth;
    private int mDay;


    public FragmentInsertNewBarcode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_insert_new_barcode, container, false);
        initComponents(view);
        initListeners();
        setText();
        setAdapter();
        return view;
    }

    private void initComponents(View view){
        streckkodid = (TextView)view.findViewById(R.id.streckkodid);
        spinnerExpense = (Spinner)view.findViewById(R.id.spinnerExpense);
        etTitel = (EditText)view.findViewById(R.id.etTitel);
        etPrice = (EditText)view.findViewById(R.id.etPrice);
        calendarView = (CalendarView)view.findViewById(R.id.calendarView);
        addBarcode = (Button)view.findViewById(R.id.addBarcode);
    }
    public void setText(){
        streckkodid.setText(((BarcodeReader)getActivity()).getBarcode());
    }

    public void setAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Expense_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerExpense.setAdapter(adapter);
    }
    public void initListeners(){
        addBarcode.setOnClickListener(new InsertBarcodeListener());
        calendarView.setOnDateChangeListener(new DateChangeListener());
    }

    private class InsertBarcodeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String expenseCatergory = spinnerExpense.getSelectedItem().toString();
            if(etPrice.getText().length() >= 1 && etTitel.getText().length() >= 1 ) {
                if (calendarView.isSelected()) {
                    BarcodeObject barcodeObject = new BarcodeObject(streckkodid.getText().toString(),
                            etTitel.getText().toString(), expenseCatergory,Double.parseDouble(etPrice.getText().toString()));
                    ExpenseObject expenseObject = new ExpenseObject(Double.parseDouble(etPrice.getText().toString()), expenseCatergory, etTitel.getText().toString(),
                            mMonth, mYear, mDay);
                    ((BarcodeReader)getActivity()).insertBarcode(barcodeObject);
                    ((BarcodeReader)getActivity()).insertExpense(expenseObject);

                }
            }

        }
    }

    private class DateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            mDay = dayOfMonth;
            mYear = year;
            mMonth = (month+1);
            calendarView.setSelected(true);
        }
    }
}

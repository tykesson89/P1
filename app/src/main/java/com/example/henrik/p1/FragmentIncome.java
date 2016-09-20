package com.example.henrik.p1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import javax.xml.datatype.Duration;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIncome extends Fragment {
        private Spinner spinnerIncome;
        private CalendarView calendarIncome;
        private EditText etIncomeTitel;
        private EditText etAmount;
        private Button btnInsertIncome;

        private SqLiteDatabase db;
        private int mYear;
        private int mDay;
        private int mMonth;

    public FragmentIncome() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_income, container, false);
        initComponents(view);
        setAdapter();
        initListeners();
        return view;
    }

    private void initComponents(View view){
        spinnerIncome = (Spinner)view.findViewById(R.id.spinnerIncome);
        calendarIncome = (CalendarView)view.findViewById(R.id.calendarIncome);
        calendarIncome.setSelected(false);
        etIncomeTitel = (EditText)view.findViewById(R.id.etIncomeTitel);
        etAmount = (EditText)view.findViewById(R.id.etAmount);
        btnInsertIncome = (Button)view.findViewById(R.id.btnInsertIncome);
    }

    public void setAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Income_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerIncome.setAdapter(adapter);
    }
    public void initListeners(){
        btnInsertIncome.setOnClickListener(new InsertIncomeListener());
        calendarIncome.setOnDateChangeListener(new DateChangeListener());
    }

    private class InsertIncomeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           String incomeCatergory = spinnerIncome.getSelectedItem().toString();
            if(etAmount.getText().length() >= 1 && etIncomeTitel.getText().length()>=1) {
                if (calendarIncome.isSelected()) {
                    IncomeObject incomeObject = new IncomeObject(Double.parseDouble(etAmount.getText().toString()), incomeCatergory, etIncomeTitel.getText().toString(), mMonth, mYear, mDay);
                    ((MainActivity) getActivity()).insertIncome(incomeObject);

                } else {
                    ((MainActivity)getActivity()).setToast("Du måste välja ett datum");

                }
            }else {
                if(etAmount.getText().length()  < 1) {
                    etAmount.setError("Skriv in belopp");
                }if(etIncomeTitel.getText().length() < 1) {
                    etIncomeTitel.setError("Skriv en titel");
                }
            }
        }
    }

    private class DateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = (month+1);
            mDay = dayOfMonth;
            calendarIncome.setSelected(true);
        }
    }
}

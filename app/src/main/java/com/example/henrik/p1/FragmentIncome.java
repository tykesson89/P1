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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIncome extends Fragment {
        private Spinner spinnerIncome;
        private CalendarView calendarIncome;
        private EditText etIncomeTitel;
        private EditText etAmount;
        private Button btnInsertIncome;
        private Controller controller;
        private SqLiteDatabase db;
        private String selectedDate;

    public FragmentIncome() {
        // Required empty public constructor
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_income, container, false);
        initComponents(view);
        setAdapter();
        db = new SqLiteDatabase(getActivity());
        db.getWritableDatabase();
        initListeners();
        return view;
    }

    private void initComponents(View view){
        spinnerIncome = (Spinner)view.findViewById(R.id.spinnerIncome);
        calendarIncome = (CalendarView)view.findViewById(R.id.calendarIncome);
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
            db.insertIncome(selectedDate, etIncomeTitel.getText().toString(), incomeCatergory, Integer.parseInt(etAmount.getText().toString()));

        }
    }

    private class DateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            selectedDate = year + "-"+month+"-"+dayOfMonth;
        }
    }
}

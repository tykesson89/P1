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



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExpense extends Fragment {
    private Spinner spinnerExpense;
    private CalendarView calendarExpense;
    private EditText etExpenseTitel;
    private EditText etPrice;
    private Button btnInsertExpense;
    private Controller controller;
    private SqLiteDatabase db;
    private String selectedDate;

    public FragmentExpense() {
        // Required empty public constructor
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_expense, container, false);
        initComponents(view);
        setAdapter();
        db = new SqLiteDatabase(getActivity());
        db.getWritableDatabase();
        initListeners();
        return view;
    }

    private void initComponents(View view){
        spinnerExpense = (Spinner)view.findViewById(R.id.spinnerExpense);
        calendarExpense = (CalendarView)view.findViewById(R.id.calendarExpense);
        etExpenseTitel = (EditText)view.findViewById(R.id.etExpenseTitel);
        etPrice = (EditText)view.findViewById(R.id.etPrice);
        btnInsertExpense = (Button)view.findViewById(R.id.btnInsertExpense);
    }

    public void setAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Expense_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerExpense.setAdapter(adapter);
    }
    public void initListeners(){
        btnInsertExpense.setOnClickListener(new InsertExpenseListener());
        calendarExpense.setOnDateChangeListener(new DateChangeListener());
    }

    private class InsertExpenseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String expenseCatergory = spinnerExpense.getSelectedItem().toString();
            db.insertExpense(selectedDate, etExpenseTitel.getText().toString(), expenseCatergory, Integer.parseInt(etPrice.getText().toString()));

        }
    }

    private class DateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            selectedDate = year + "-"+month+"-"+dayOfMonth;
        }
    }
}
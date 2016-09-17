package com.example.henrik.p1;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ViewExpense extends Fragment {

    private Button btnExpenseFromDate;
    private Button btnExpenseToDate;
    private ListView lvExpenseView;

    public Fragment_ViewExpense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__view_expense, container, false);
        initComponents(view);
        initListeners();
        setAdapter();
        return view;
    }

    private void initComponents(View view) {
        btnExpenseFromDate = (Button) view.findViewById(R.id.btnExpenseFromDate);
        btnExpenseToDate = (Button) view.findViewById(R.id.btnExpenseToDate);
        lvExpenseView = (ListView) view.findViewById(R.id.lvExpenseView);

    }
    private void initListeners(){
        btnExpenseToDate.setOnClickListener(new ButtonExpenseToDateListener());
        btnExpenseFromDate.setOnClickListener(new ButtonExpenseFromDateListener());
    }


    public void openToDate(View view){
        CalendarView calendarView = new CalendarView(getActivity());
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(calendarView);
        calendarView.setOnDateChangeListener(new ToDateChangeListener());
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void openFromDate(View view){
        CalendarView calendarView = new CalendarView(getActivity());
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(calendarView);
        calendarView.setOnDateChangeListener(new FromDateChangeListener());
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private class ButtonExpenseToDateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            openToDate(v);
        }
    }

    private class ToDateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

            btnExpenseToDate.setText(year + "-" + (month+1) + "-" + dayOfMonth);
        }
    }

    private class FromDateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            String str = year + "-" + (month+1) + "-" + dayOfMonth;

            btnExpenseFromDate.setText(str);
        }
    }

    private class ButtonExpenseFromDateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            openFromDate(v);
        }
    }

    public void setAdapter(){
        AdapterExpense adapterExpense;
        ArrayList<ExpenseObject> myListItems  = new ArrayList<ExpenseObject>();
        SqLiteDatabase sqLiteDatabase = new SqLiteDatabase(getActivity());
        myListItems = sqLiteDatabase.getAllExpenseObjects();


        adapterExpense= new AdapterExpense (getActivity(), 0, myListItems);
        lvExpenseView.setAdapter(adapterExpense);
    }

}

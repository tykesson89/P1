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
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ViewIncome extends Fragment {
    private Button btnIncomeFromDate;
    private Button btnIncomeToDate;
    private ListView lvIncomeView;

    public Fragment_ViewIncome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__view_income, container, false);
        initComponents(view);
        initListeners();
        return view;
    }

    private void initComponents(View view) {
        btnIncomeFromDate = (Button) view.findViewById(R.id.btnIncomeFromDate);
        btnIncomeToDate = (Button) view.findViewById(R.id.btnIncomeToDate);
        lvIncomeView = (ListView) view.findViewById(R.id.lvIncomeView);

    }
    private void initListeners(){
        btnIncomeToDate.setOnClickListener(new ButtonIncomeToDateListener());
        btnIncomeFromDate.setOnClickListener(new ButtonIncomeFromDateListener());
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

    private class ButtonIncomeToDateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            openToDate(v);
        }
    }

    private class ToDateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

            btnIncomeToDate.setText(year + "-" + (month+1) + "-" + dayOfMonth);
        }
    }

    private class FromDateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            String str = year + "-" + (month+1) + "-" + dayOfMonth;

            btnIncomeFromDate.setText(str);
        }
    }

    private class ButtonIncomeFromDateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            openFromDate(v);
        }
    }
}





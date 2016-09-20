package com.example.henrik.p1;



import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ViewIncome extends Fragment {
    private Button btnIncomeFromDate;
    private Button btnIncomeToDate;
    private Button btnChooseIncome;
    private ListView lvIncomeView;
    private TextView tvDialogCategoryIncome;
    private TextView tvDialogAmountIncome;
    private TextView tvDialogTitelIncome;
    private TextView tvDialogDateIncome;
    private String dateFrom;
    private String dateTo;
    private ArrayList<IncomeObject> myListItems  = new ArrayList<IncomeObject>();

    public Fragment_ViewIncome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__view_income, container, false);
        initComponents(view);
        initListeners();
        initMyListItem();
        return view;
    }

    private void initComponents(View view) {
        btnIncomeFromDate = (Button) view.findViewById(R.id.btnIncomeFromDate);
        btnIncomeToDate = (Button) view.findViewById(R.id.btnIncomeToDate);
        btnChooseIncome = (Button) view.findViewById(R.id.btnChooseIncome);
        lvIncomeView = (ListView) view.findViewById(R.id.lvIncomeView);

    }
    private void initListeners(){
        btnIncomeToDate.setOnClickListener(new ButtonIncomeToDateListener());
        btnIncomeFromDate.setOnClickListener(new ButtonIncomeFromDateListener());
        lvIncomeView.setOnItemClickListener(new ItemClickListener());
        btnChooseIncome.setOnClickListener(new ButtonChooseListener());
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
            dateTo = year + "-" + (month+1) + "-" + dayOfMonth;
            btnIncomeToDate.setText(dateTo);


        }
    }

    private class FromDateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
             dateFrom = year + "-" + (month+1) + "-" + dayOfMonth;

            btnIncomeFromDate.setText(dateFrom);

        }
    }

    private class ButtonIncomeFromDateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            openFromDate(v);
        }
    }
    public void initMyListItem(){
        myListItems = ((MainActivity)getActivity()).getIncomeListFromDB();
     setAdapter(myListItems);
    }

    public void setAdapter(ArrayList<IncomeObject> arrayList){
        AdapterIncome adapterIncome;
        adapterIncome= new AdapterIncome (getActivity(), 0, arrayList);
        lvIncomeView.setAdapter(adapterIncome);
    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View content =  inflater.inflate(R.layout.dialog_layout_income, null);
            dialogBuilder.setView(content);
            tvDialogCategoryIncome = (TextView)content.findViewById(R.id.tvDialogcategoryIncome);
            tvDialogAmountIncome = (TextView)content.findViewById(R.id.tvDialogAmountIncome);
            tvDialogTitelIncome = (TextView)content.findViewById(R.id.tvDialogTitelIncome);
            tvDialogDateIncome = (TextView)content.findViewById(R.id.tvDialogDateIncome);
            tvDialogCategoryIncome.setText(myListItems.get(position).getCategory());
            tvDialogAmountIncome.setText(Double.toString(myListItems.get(position).getAmount()));
            tvDialogTitelIncome.setText(myListItems.get(position).getTitel());
            tvDialogDateIncome.setText(myListItems.get(position).getFormatedDate());

            dialogBuilder.show();



        }
    }

    private class ButtonChooseListener implements View.OnClickListener {

        ArrayList<IncomeObject> arrayList = new ArrayList<>();
        @Override
        public void onClick(View v) {
            arrayList = ((MainActivity)getActivity()).getSortedIncomeList(dateFrom, dateTo);
            setAdapter(arrayList);
        }
    }
}





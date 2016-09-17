package com.example.henrik.p1;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ViewExpense extends Fragment {

    private Button btnExpenseFromDate;
    private Button btnExpenseToDate;
    private Button btnChooseExpense;
    private ListView lvExpenseView;
    private String dateFrom;
    private String dateTo;
    private ArrayList<ExpenseObject> myListItems  = new ArrayList<ExpenseObject>();
    private TextView tvDialogCategoryExpense;
    private TextView tvDialogPriceExpense;
    private TextView tvDialogTitelExpense;
    private TextView tvDialogDateExpense;
    public Fragment_ViewExpense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment__view_expense, container, false);
        initComponents(view);
        initListeners();
        initMyListItem();
        return view;
    }

    private void initComponents(View view) {
        btnExpenseFromDate = (Button) view.findViewById(R.id.btnExpenseFromDate);
        btnExpenseToDate = (Button) view.findViewById(R.id.btnExpenseToDate);
        btnChooseExpense = (Button) view.findViewById(R.id.btnChooseExpense);
        lvExpenseView = (ListView) view.findViewById(R.id.lvExpenseView);


    }
    private void initListeners(){
        btnExpenseToDate.setOnClickListener(new ButtonExpenseToDateListener());
        btnExpenseFromDate.setOnClickListener(new ButtonExpenseFromDateListener());
        lvExpenseView.setOnItemClickListener(new ItemClickListener());
        btnChooseExpense.setOnClickListener(new ButtonChooseListener());
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
            dateTo = year + "-" + (month+1) + "-" + dayOfMonth;
            btnExpenseToDate.setText(dateTo);
        }
    }

    private class FromDateChangeListener implements CalendarView.OnDateChangeListener {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            dateFrom = year + "-" + (month+1) + "-" + dayOfMonth;

            btnExpenseFromDate.setText(dateFrom);
        }
    }

    private class ButtonExpenseFromDateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            openFromDate(v);
        }
    }

    public void initMyListItem(){

        SqLiteDatabase sqLiteDatabase = new SqLiteDatabase(getActivity());
        myListItems = sqLiteDatabase.getAllExpenseObjects();
        setAdapter(myListItems);
    }

    public void setAdapter(ArrayList<ExpenseObject> arrayList){


        AdapterExpense adapterExpense;
        adapterExpense= new AdapterExpense (getActivity(), 0, arrayList);
        lvExpenseView.setAdapter(adapterExpense);
    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View content =  inflater.inflate(R.layout.dialog_layout, null);
            dialogBuilder.setView(content);
            tvDialogCategoryExpense = (TextView)content.findViewById(R.id.tvDialogcategoryExpense);
            tvDialogPriceExpense = (TextView)content.findViewById(R.id.tvDialogPriceExpense);
            tvDialogTitelExpense = (TextView)content.findViewById(R.id.tvDialogTitelExpense);
            tvDialogDateExpense = (TextView)content.findViewById(R.id.tvDialogDateExpense);
            tvDialogCategoryExpense.setText(myListItems.get(position).getCategory());
            tvDialogPriceExpense.setText(Double.toString(myListItems.get(position).getPrice()));
            tvDialogTitelExpense.setText(myListItems.get(position).getTitel());
            tvDialogDateExpense.setText(myListItems.get(position).getFormatedDate());

            dialogBuilder.show();



        }
    }
    private class ButtonChooseListener implements View.OnClickListener {


        ArrayList<ExpenseObject> arrayList = new ArrayList<>();
        @Override
        public void onClick(View v) {
            LocalDate dateStart = new LocalDate(dateFrom);
            LocalDate dateEnd = new LocalDate(dateTo);

            arrayList.clear();
            for(int i = 0; i < myListItems.size(); i++){
                LocalDate current = new LocalDate(myListItems.get(i).getFormatedDate());

                if(current.isAfter(dateStart)&&current.isBefore(dateEnd)||current.isEqual(dateStart)||current.isEqual(dateEnd)){
                    arrayList.add(myListItems.get(i));
                    Log.d(current.toString(),"hej");
                }

            }
            setAdapter(arrayList);

        }
    }
}

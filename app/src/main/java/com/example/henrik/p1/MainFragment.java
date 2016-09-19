package com.example.henrik.p1;




import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainFragment extends Fragment {
    private Button btnNewIncome;
    private Button btnNewExpense;
    private Button btnViewAllIncome;
    private Button btnViewAllExpense;
    private TextView tvName;
    private TextView tvIncome;
    private TextView tvExpense;
    private TextView tvTotal;
    SqLiteDatabase db;



    public MainFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main, container, false);
        initComponents(view);
        db = new SqLiteDatabase(getActivity());
       initListeners();
        setMenutext();
        return view;
    }



    private void initComponents(View view){
        btnNewExpense = (Button)view.findViewById(R.id.btnNewExpense);
        btnNewIncome = (Button)view.findViewById(R.id.btnNewIncome);
        btnViewAllIncome = (Button)view.findViewById(R.id.btnViewAllIncome);
        btnViewAllExpense = (Button)view.findViewById(R.id.btnViewAllExpense);
        tvName = (TextView)view.findViewById(R.id.tvName);
        tvIncome = (TextView)view.findViewById(R.id.tvIncome);
        tvExpense = (TextView)view.findViewById(R.id.tvExpense);
        tvTotal = (TextView)view.findViewById(R.id.tvTotal);
    }
    private void initListeners(){
        btnNewIncome.setOnClickListener(new ButtonNewIncomeListener());
        btnNewExpense.setOnClickListener(new ButtonNewExpenseListener());
        btnViewAllIncome.setOnClickListener(new ButtonViewAllIncomeListener());
        btnViewAllExpense.setOnClickListener(new ButtonViewAllExpenseListener());
    }
    private void setMenutext(){
        tvName.setText(((MainActivity)getActivity()).getSharedPreferences());
        tvIncome.setText(Double.toString(db.getAllIncome()));
        tvExpense.setText(Double.toString(db.getAllExpense()));
        tvTotal.setText(Double.toString(db.getAllIncome()-db.getAllExpense()));
    }


    private class ButtonNewExpenseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).swapFrag(new FragmentExpense());
        }
    }

    private class ButtonNewIncomeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).swapFrag(new FragmentIncome());
        }
    }


    private class ButtonViewAllIncomeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).swapFrag(new Fragment_ViewIncome());
        }
    }

    private class ButtonViewAllExpenseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).swapFrag(new Fragment_ViewExpense());
        }
    }
}

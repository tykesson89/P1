package com.example.henrik.p1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Henrik on 2016-09-16.
 */
public class AdapterIncome extends ArrayAdapter<IncomeObject> {
    private Activity activity;
    private ArrayList<IncomeObject> incomeArray;
    private static LayoutInflater inflater = null;

    public AdapterIncome (Activity activity, int textViewResourceId, ArrayList<IncomeObject> incomeArray) {
        super(activity, textViewResourceId, incomeArray);
        try {
            this.activity = activity;
            this.incomeArray = incomeArray;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {

        return incomeArray.size();
    }

    @Override
    public IncomeObject getItem(int position) {
        return super.getItem(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView tvIncomeCategory;
        public TextView tvIncomeTitel;
        public TextView tvIncomeAmount;
        public TextView tvIncomeDate;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.income_list_layout, null);
                holder = new ViewHolder();

                holder.tvIncomeCategory = (TextView) vi.findViewById(R.id.tvIncomeCategory);
                holder.tvIncomeTitel = (TextView) vi.findViewById(R.id.tvExpenseTitel);
                holder.tvIncomeAmount = (TextView) vi.findViewById(R.id.tvExpensePrice);
                holder.tvIncomeDate = (TextView) vi.findViewById(R.id.tvExpenseDate);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.tvIncomeCategory.setText(incomeArray.get(position).getCategory());
            holder.tvIncomeTitel.setText(incomeArray.get(position).getTitel());
            holder.tvIncomeAmount.setText(Double.toString(incomeArray.get(position).getAmount()));
            holder.tvIncomeDate.setText(incomeArray.get(position).getFormatedDate());


        } catch (Exception e) {


        }
        return vi;
    }
}

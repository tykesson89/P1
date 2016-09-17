package com.example.henrik.p1;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Henrik on 2016-09-17.
 */
public class AdapterExpense extends ArrayAdapter<ExpenseObject> {

    private Activity activity;
    private ArrayList<ExpenseObject> expenseArray;
    private static LayoutInflater inflater = null;

    public AdapterExpense (Activity activity, int textViewResourceId, ArrayList<ExpenseObject> expenseArray) {
        super(activity, textViewResourceId, expenseArray);
        try {
            this.activity = activity;
            this.expenseArray= expenseArray;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {

        return expenseArray.size();
    }

    @Override
    public ExpenseObject getItem(int position) {
        return super.getItem(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public ImageView ivExpenseCategory;
        public TextView tvExpenseTitel;
        public TextView tvExpensePrice;
        public TextView tvExpenseDate;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.expense_list_layout, null);
                holder = new ViewHolder();

                holder.ivExpenseCategory = (ImageView) vi.findViewById(R.id.ivExpenseCategory);
                holder.tvExpenseTitel = (TextView) vi.findViewById(R.id.tvExpenseTitel);
                holder.tvExpensePrice = (TextView) vi.findViewById(R.id.tvExpensePrice);
                holder.tvExpenseDate = (TextView) vi.findViewById(R.id.tvExpenseDate);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }


            if(expenseArray.get(position).getCategory().equals("Boende")) {
                holder.ivExpenseCategory.setImageResource(R.mipmap.boende);
            }else if(expenseArray.get(position).getCategory().equals("Fritid")){
                holder.ivExpenseCategory.setImageResource(R.mipmap.fritid);
            }else if(expenseArray.get(position).getCategory().equals("Livsmedel")){
                holder.ivExpenseCategory.setImageResource(R.mipmap.livsmedel);
            }else if(expenseArray.get(position).getCategory().equals("Övrigt")){
                holder.ivExpenseCategory.setImageResource(R.mipmap.ovrigt);
            }else if(expenseArray.get(position).getCategory().equals("Resor")){
                holder.ivExpenseCategory.setImageResource(R.mipmap.resor);
            }


            holder.tvExpenseTitel.setText(expenseArray.get(position).getTitel());
            holder.tvExpensePrice.setText(Double.toString(expenseArray.get(position).getPrice()));
            holder.tvExpenseDate.setText(expenseArray.get(position).getFormatedDate());


        } catch (Exception e) {


        }
        return vi;
    }
}

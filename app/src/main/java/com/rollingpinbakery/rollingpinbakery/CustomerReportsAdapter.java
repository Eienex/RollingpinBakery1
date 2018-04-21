package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.util.ArrayList;

public class CustomerReportsAdapter extends ArrayAdapter<Customer>
implements View.OnClickListener{

    private ArrayList<Customer> dataset;
    Context mContext;


    private static class ViewHolder {
        TextView custType;
        TextView CustId;
        TextView name;
        TextView email;
    }

    public CustomerReportsAdapter(ArrayList<Customer> data, Context context) {
        super(context, R.layout.c_reports_listview, data);
        this.dataset = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer customer = getItem(position);
        CustomerReportsAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new CustomerReportsAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.c_reports_listview, parent, false);
            viewHolder.custType = convertView.findViewById(R.id.custType);
            viewHolder.CustId = convertView.findViewById(R.id.CustId);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.email = convertView.findViewById(R.id.email);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CustomerReportsAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.custType.setText("Customer Role: " + customer.getCustType());
        viewHolder.CustId.setText("Customer ID: " + customer.get_custId());
        viewHolder.name.setText("Name: " + customer.getCustFName() + " " + customer.getCustLName());
        viewHolder.email.setText("Email: " + customer.getCustEmail());


        return convertView;
    }


    @Override
    public void onClick(View v) {
    }
}



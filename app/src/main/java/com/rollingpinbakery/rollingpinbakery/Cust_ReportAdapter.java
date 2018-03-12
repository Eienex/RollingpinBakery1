package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.util.ArrayList;

/**
 * Created by jamej on 3/11/2018.
 */

public class Cust_ReportAdapter extends ArrayAdapter<Customer> {
    private ArrayList<Customer> dataSet;
    Context context;

    public Cust_ReportAdapter(Context context, ArrayList<Customer> customers){
        super(context, R.layout.c_reports_listview, customers);
        this.dataSet = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Customer customer = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.c_reports_listview, parent, false);

        final TextView CustomerID = convertView.findViewById(R.id.CustId);
        final TextView FirstName = convertView.findViewById(R.id.fName);
        final TextView LastName = convertView.findViewById(R.id.lName);
        final TextView email = convertView.findViewById(R.id.email);


        CustomerID.setText("Customer ID: " + customer.get_custId());
        FirstName.setText("First Name: " + customer.getCustFName());
        LastName.setText("Last Name: " + customer.getCustLName());
        email.setText("Email: " + customer.getCustEmail());

        return convertView;
    }
}

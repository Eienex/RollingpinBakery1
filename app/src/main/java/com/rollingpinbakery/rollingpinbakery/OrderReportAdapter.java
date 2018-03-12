package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rollingpinbakery.rollingpinbakery.Data.Order;

import java.util.ArrayList;


public class OrderReportAdapter extends ArrayAdapter<Order> {

    private ArrayList<Order> dataSet;
    Context context;

    public OrderReportAdapter(Context context, ArrayList<Order> orders){
        super(context, R.layout.o_reports_listview, orders);
        this.dataSet = orders;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Order order = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.o_reports_listview, parent, false);

        final TextView OrderID = convertView.findViewById(R.id.OrderId);
        final TextView PaymentID = convertView.findViewById(R.id.PaymentId);
        final TextView CustID = convertView.findViewById(R.id.CustId);



        OrderID.setText("Order ID: " + order.get_orderId());
        PaymentID.setText("Payment ID: " + order.getPaymentId());
        CustID.setText("Customer ID: " + order.getCustId());

        return convertView;
    }
}
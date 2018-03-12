package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rollingpinbakery.rollingpinbakery.Data.Product;

import java.util.ArrayList;


public class Invent_ReportAdapter  extends ArrayAdapter<Product> {
    private ArrayList<Product> dataSet;
    Context context;

    public Invent_ReportAdapter(Context context, ArrayList<Product> products){
        super(context, R.layout.i_reports_listview, products);
        this.dataSet = products;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Product product = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.i_reports_listview, parent, false);

        final TextView ProductID = convertView.findViewById(R.id.ProductId);
        final TextView ProductName = convertView.findViewById(R.id.ProductName);
        final TextView ProductPrice = convertView.findViewById(R.id.ProductPrice);
        final TextView ProductType = convertView.findViewById(R.id.ProductType);
        final TextView ProductDesc = convertView.findViewById(R.id.ProductDescription);


        ProductID.setText("Product ID: " + product.get_prodId());
        ProductName.setText("Product Name: " + product.getProdName());
        ProductPrice.setText("Price: " + product.getProdRetailPrice());
        ProductType.setText("Product Type: " + product.getProdType());
        ProductDesc.setText("Description: " + product.getProdDesc());

        return convertView;
    }
}


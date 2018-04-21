package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

import java.util.ArrayList;


        public class InventoryReportsAdapter  extends ArrayAdapter<Product> {
            private ArrayList<Product> dataSet;
            Context context;
            String onSale;

            private static class ViewHolder{
                TextView ProductName;
                TextView ProductPrice;
                TextView ProductType;
                TextView onSale;
                TextView SalePrice;
            }

            public InventoryReportsAdapter(Context context, ArrayList<Product> products){
                super(context, R.layout.i_reports_listview, products);
                this.dataSet = products;
                this.context = context;
            }

            public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
                final Product product = getItem(postition);
                InventoryReportsAdapter.ViewHolder viewHolder;

                final View result;

                if (convertView == null) {
                    viewHolder = new InventoryReportsAdapter.ViewHolder();
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.i_reports_listview, parent, false);


                    viewHolder.ProductName = convertView.findViewById(R.id.ProductName);
                    viewHolder.ProductPrice = convertView.findViewById(R.id.ProductPrice);
                    viewHolder.ProductType = convertView.findViewById(R.id.ProductType);
                    viewHolder.onSale = convertView.findViewById(R.id.onSale);
                    viewHolder.SalePrice = convertView.findViewById(R.id.SalePrice);


                    result = convertView;

                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (InventoryReportsAdapter.ViewHolder) convertView.getTag();
                    result = convertView;
                }

                viewHolder.ProductName.setText("Name: " + product.getProdName());
                viewHolder.ProductPrice.setText("Price: " + product.getProdRetailPrice());
                viewHolder.ProductType.setText("Category: " + product.getProdType());
                if(product.getProdSalePrice() == 0){
                    viewHolder.onSale.setText("On Sale: No");
                    viewHolder.SalePrice.setText("Sale Price: --");
                }
                else if(product.getProdSalePrice() != 0){
                    viewHolder.onSale.setText("On Sale: Yes");
                    viewHolder.SalePrice.setText("Sale Price: "+ product.getProdSalePrice());
                }

                return convertView;
            }

        }

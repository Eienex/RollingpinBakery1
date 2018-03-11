package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.Product;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;

import java.util.ArrayList;

/**
 * Created by rudst on 2/28/2018.
 */

public class StoreProductAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> dataSet;
    Context context;

    public StoreProductAdapter(Context context, ArrayList<Product> products){
        super(context, R.layout.store_row_item, products);
        this.dataSet = products;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Product product = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.store_row_item, parent, false);

        final TextView name = convertView.findViewById(R.id.name);
        final TextView price = convertView.findViewById(R.id.price);
        final TextView salePrice = convertView.findViewById(R.id.salePrice);
        final TextView productType = convertView.findViewById(R.id.productType);
        final TextView productDesc = convertView.findViewById(R.id.productDesc);
        Button viewBtn = convertView.findViewById(R.id.ViewBtn);
        Button buyBtn = convertView.findViewById(R.id.BuyBtn);

        final int id = product.get_prodId();

        name.setText("Name: " + product.getProdName());
        price.setText("Price: " + product.getProdRetailPrice());
        if(product.getProdSalePrice() != null) {
            salePrice.setText("Sale Price: " + product.getProdSalePrice());
        }
        else{
            salePrice.setText("Sale Price: None");
        }
        productType.setText("Category: " + product.getProdType());
        productDesc.setText("Description: " + product.getProdDesc());


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtName = name.getText().toString();
                final String txtPrice = price.getText().toString();
                final String txtSalePrice = salePrice.getText().toString();
                final String txtType = productType.getText().toString();
                final String txtDesc = productDesc.getText().toString();
                viewProduct(view, id, txtName, txtPrice, txtSalePrice, txtType, txtDesc);
            }
        });

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String txtName = name.getText().toString();
                final String txtPrice = price.getText().toString();
                final String txtSalePrice = salePrice.getText().toString();
                final String txtType = productType.getText().toString();
                final String txtDesc = productDesc.getText().toString();
                cart(view, id, txtName, txtPrice, txtSalePrice, txtType, txtDesc);
            }
        });

        return convertView;
    }

    public void reset(Product product){
        this.remove(product);
        notifyDataSetChanged();
    }

    public void update(Product product){
        AppDatabase.getAppDatabase(getContext()).productDao().delete(product);
    }

    public void viewProduct(View view, int id, String name, String price, String salePrice, String type, String desc){
        Intent formResult = new Intent(getContext(), Item.class);

        formResult.putExtra("int_productID", id);
        formResult.putExtra("txt_productName", name);
        formResult.putExtra("txt_productPrice",price);
        formResult.putExtra("txt_productSalePrice", salePrice);
        formResult.putExtra("txt_productType", type);
        formResult.putExtra("txt_productDesc", desc);

        view.getContext().startActivity(formResult);
    }

    public void cart(View view, int id, String name, String price, String salePrice, String type, String desc){
        Intent formResult = new Intent(getContext(), Item.class);

        formResult.putExtra("int_productID", id);
        formResult.putExtra("txt_productName", name);
        formResult.putExtra("txt_productPrice",price);
        formResult.putExtra("txt_productSalePrice", salePrice);
        formResult.putExtra("txt_productType", type);
        formResult.putExtra("txt_productDesc", desc);

        view.getContext().startActivity(formResult);
    }
}

package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;
import java.util.ArrayList;

public class CartProductsAdapter extends ArrayAdapter<Cart>{

    ArrayList<Cart> carts;
    private ArrayList<Cart> dataSet;
    Context context;

    public CartProductsAdapter(Context context, ArrayList<Cart> cartProducts){
        super(context, R.layout.row_item_cart_products, cartProducts);
        this.dataSet = cartProducts;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int postition, @Nullable View convertView, @NonNull final ViewGroup parent){
        final Cart cart = getItem(postition);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_item_cart_products, parent, false);

        final TextView Qty = convertView.findViewById(R.id.Qty);
        final TextView prodName = convertView.findViewById(R.id.prodName);
        final TextView txtProdPrice = convertView.findViewById(R.id.txtProdPrice);

        carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(context)
                .cartDao()
                .getAllCartItems();

        for(int i=0; i <carts.size(); i++){
            String productName = cart.getItemName();
            String productPrice = cart.getPrice();

            int qty = cart.getQty();
            productName = productName.replace("Name: ", "");
            productPrice = productPrice.replace("Sale Price: ", "");


            prodName.setText(productName);
            txtProdPrice.setText("$"+ productPrice);
            Qty.setText(""+ qty);
        }
        return convertView;
    }

}

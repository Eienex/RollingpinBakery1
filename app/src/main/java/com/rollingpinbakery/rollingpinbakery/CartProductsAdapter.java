package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;

import java.util.ArrayList;

public class CartProductsAdapter extends ArrayAdapter<Cart>{

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

        return convertView;
    }

}

package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;

import java.util.ArrayList;

/**
 * Created by defco on 3/11/2018.
 */

public class CartAdapter extends ArrayAdapter<Cart> {

    private ArrayList<Cart> dataSet;
    Context context;

    public CartAdapter(Context context, ArrayList<Cart> carts){
        super(context, R.layout.row_cart_item, carts);
        this.dataSet = carts;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Cart cart = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_cart_item,parent, false);
        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemCat = convertView.findViewById(R.id.itemCat);
        TextView price = convertView.findViewById((R.id.price));
        TextView qty = convertView.findViewById(R.id.qty);
        ImageButton btn = convertView.findViewById(R.id.deleteBtn);
        ImageButton btnAdd = convertView.findViewById(R.id.addBtn);
        ImageButton btnSubtract = convertView.findViewById(R.id.subtractBtn);

        itemName.setText(cart.getItemName());
        itemCat.setText(cart.getItemCat());
        qty.setText("Qty: " + cart.getQty());
        price.setText("Price: " + cart.getPrice());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataSet =(ArrayList<Cart>) AppDatabase.getAppDatabase(getContext()).cartDao().getAllCartItems();
                AppDatabase.getAppDatabase(context).cartDao().delete(cart);
                reset(cart);
                context.startActivity(new Intent(context, CartActivity.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQty = cart.getQty();
                int newQty = currentQty +1;
                cart.setQty(newQty);
                AppDatabase.getAppDatabase(context).cartDao().update(cart);
                context.startActivity(new Intent(context, CartActivity.class));

            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQty = cart.getQty();
                int newQty = currentQty -1;
                if(newQty==0){
                    AppDatabase.getAppDatabase(context).cartDao().delete(cart);
                    reset(cart);
                    context.startActivity(new Intent(context, CartActivity.class));
                }else{
                    cart.setQty(newQty);
                    AppDatabase.getAppDatabase(context).cartDao().update(cart);
                    context.startActivity(new Intent(context, CartActivity.class));
                }

            }
        });

        return convertView;
    }

    public void reset(Cart cart){
        this.remove(cart);
        notifyDataSetChanged();
    }
}

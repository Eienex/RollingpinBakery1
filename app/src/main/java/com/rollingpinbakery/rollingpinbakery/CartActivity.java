package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    CartAdapter cad;
    ListView listView;
    ArrayList<Cart> carts;
    private static CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        FloatingActionButton backfab = findViewById(R.id.back);
        backfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                startActivity(new Intent(getBaseContext(), Store.class));
            }
        });


    }

    public void proceedToShipping(View view) {
        /*This will also transfer the items from cart over to order.
        * Maybe this could be done after they have entered their info first?*/
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.ShippingInfoPage"));
    }
    /*
    * This will display all items from the cart.
    * If there are no items to be displayed, a message
    * will show telling the user.*/
    @Override
    protected void onResume() {
        super.onResume();
        /*
        listView = findViewById(R.id.listView);

        carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(this)
                .cartDao()
                .getAllCartItems();

        adapter = new CartAdapter(this, carts);

            listView.setAdapter(adapter);
         */

    }
}

package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    CartAdapter cad;
    ListView listView;
    ArrayList<Cart> carts;
    private static CartAdapter adapter;
    public Intent cartResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        FloatingActionButton backfab = findViewById(R.id.back);
        backfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), Store.class));
            }
        });

        listView = findViewById(R.id.listView);
        /*carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(this)
                .cartDao()
                .getAllCartItems();
        if(carts.isEmpty()){
            TextView cartText = findViewById(R.id.CartEmptyText);
            cartText.setText("You currently have no products in the cart");
            //Toast.makeText(getApplicationContext(),"You currently have no products in the cart", Toast.LENGTH_LONG).show();
        }else{
            adapter = new CartAdapter(this, carts);
            listView.setAdapter(adapter);
        }*/
    }

    public void proceedToShipping(View view) {
        /*This will also transfer the items from cart over to order.
        * Maybe this could be done after they have entered their info first?*/
        if(carts.isEmpty()){
            Toast.makeText(getApplicationContext(), "The cart is empty", Toast.LENGTH_SHORT).show();
        }else{
            cartResults = new Intent(getApplicationContext(), ShippingInfoPage.class);
            for(int i = 0; i <carts.size(); i++){
                String productName = carts.get(i).getItemName();
                String productPrice = carts.get(i).getPrice();
                String nameFinal = productName.replace("Name: ","");
                String priceFinal = productPrice.replace("Sale Price: ","");
                String prodExtraName = ("Product").concat(Integer.toString(i));
                String priceExtraName = ("Price").concat(Integer.toString(i));
                cartResults.putExtra(prodExtraName , nameFinal);
                cartResults.putExtra(priceExtraName, priceFinal);
            }
            startActivity(cartResults);
            //startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.ShippingInfoPage"));
        }

    }
    /*
    * This will display all items from the cart.
    * If there are no items to be displayed, a message
    * will show telling the user.*/
    @Override
    protected void onResume() {
        super.onResume();



    }
}

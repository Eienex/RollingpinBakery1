package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        FloatingActionButton purchasefab = findViewById(R.id.purchase);
        purchasefab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Thank you for your purchase!", Toast.LENGTH_LONG).show();
//

            }
        });

    }
}

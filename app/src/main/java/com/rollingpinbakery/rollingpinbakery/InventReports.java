package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

import java.util.ArrayList;

public class InventReports extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference dbReference;


    ListView listView;
    ArrayList<Product> products;
    private static InventoryReportsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reports_invent);

        firebaseAuth=FirebaseAuth.getInstance();

        listView = findViewById(R.id.product_list);
        products = new ArrayList<>();


        dbReference = FirebaseDatabase.getInstance().getReference("Products");
        dbReference.orderByChild("prodRetailPrice").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Product product = snapshot.getValue(Product.class);

                    //String productID = product.get_prodId();
                    String productName = product.getProdName();
                    String productType = product.getProdType();
                    Double productSalePrice = product.getProdSalePrice();
                    String onSale = product.getOnSale();
                    Double productPrice = product.getProdRetailPrice();

                    products.add(new Product( productName, productPrice, productSalePrice, onSale, productType));
                }
                adapter = new InventoryReportsAdapter(getApplicationContext(),products);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {   }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();

    }

}

package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class CustReports extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference dbReference;

    ListView listView;
    ArrayList<Customer> customers;
    private static CustomerReportsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_reports_customer);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        listView = findViewById(R.id.list_item);
        customers = new ArrayList<>();


        dbReference = FirebaseDatabase.getInstance().getReference("users");
        dbReference.orderByChild("custType").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Customer customer = snapshot.getValue(Customer.class);

                    String custID = customer.get_custId();
                    String custFName = customer.getCustFName();
                    String custLName = customer.getCustLName();
                    //String userName = customer.getCustUsername();
                    String custEmail = customer.getCustEmail();
                   // String custPassword = customer.getCustPassword();
                    String custType = customer.getCustType();


                    customers.add(new Customer(custID, custFName, custLName, custEmail, custType));

                }
                adapter = new CustomerReportsAdapter(customers, getApplicationContext());
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

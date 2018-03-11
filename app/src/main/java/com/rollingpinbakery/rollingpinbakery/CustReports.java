package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustReports extends AppCompatActivity {

    ListView listView;
    ArrayList<Customer> customers;
    private static CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_reports);

    }

    @Override
    protected void onResume(){
        super.onResume();
        listView = findViewById(R.id.list_item);
        customers = (ArrayList<Customer>) AppDatabase.getAppDatabase(this).customerDao().getCustReports();
        adapter = new CustomerAdapter(this, customers);
        listView.setAdapter(adapter);
    }

}

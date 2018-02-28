package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.lang.reflect.Array;
import java.util.List;

public class CustReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_reports);

    }

}

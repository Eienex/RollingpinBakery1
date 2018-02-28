package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
    }


    public void custReports(View view) {
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.CustReports"));
    }
    public void orderReports(View view){
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.OrderReports"));
    }

    public void inventReports(View view) {
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.InventReports"));
    }
}

package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public String customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Submit(View v){
        //Gets values
        EditText e = findViewById(R.id.editText);
        String test1 = e.getText().toString();
        EditText f = findViewById(R.id.editText2);
        String test2 = f.getText().toString();

        //Shows that values have been retrieved
        customer = (AppDatabase.getAppDatabase(this)
                .customerDao()
                .getCustomerInfo(test1, test2)).toString();
        if(customer == null){
            Toast.makeText(getApplicationContext(), "Username does not exist",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Welcome back " + customer + "!", Toast.LENGTH_SHORT).show();
        }

    }

}

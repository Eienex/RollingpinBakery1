package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;

import java.util.List;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
    }

    public void SubmitRegistration(View v) {
        //Gets values
        EditText fName = findViewById(R.id.fName);
        EditText lName = findViewById(R.id.lName);
        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        EditText email = findViewById(R.id.email);

        String fNameText = fName.getText().toString();
        String lNameText = lName.getText().toString();
        String userNameText = userName.getText().toString();
        String passwordText = password.getText().toString();
        String emailText = email.getText().toString();


        AppDatabase.getAppDatabase(this).customerDao().insert(
                new Customer(fNameText, lNameText, userNameText, passwordText, emailText, "Customer"));
        finish();

      //  List<Customer> = AppDatabase.getAppDatabase(this).customerDao().getAllCustomers().toString();

        Toast.makeText(this,"Welcome" + fNameText, Toast.LENGTH_LONG).show();

    }
}


package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;

import java.util.List;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
    }

    public void SubmitRegistration(View v) {
        //Gets values
        try {

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

            if(fNameText.isEmpty() || lNameText.isEmpty() || userNameText.isEmpty() || passwordText.isEmpty() || emailText.isEmpty()){
                Toast.makeText(this, "Fill out the form correctly", Toast.LENGTH_LONG).show();
            }else {
                try{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
                    databaseAccess.open();
                    //AppDatabase.getAppDatabase(this).customerDao().insert(new Customer(fNameText, lNameText, userNameText, passwordText, emailText, "Customer"));


                    databaseAccess.insertCustomer(new Customer(fNameText, lNameText, userNameText, passwordText, emailText, "Customer"));
                    Toast.makeText(this, "Welcome to the Rolling Pin Bakery, " + fNameText, Toast.LENGTH_LONG).show();
                    databaseAccess.close();
                    this.finish();
                    startActivity(new Intent(this, LoginActivity.class));

                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}


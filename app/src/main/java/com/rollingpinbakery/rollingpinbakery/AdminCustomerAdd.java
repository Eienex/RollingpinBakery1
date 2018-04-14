package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

public class AdminCustomerAdd extends AppCompatActivity {

    String[] customerTypes;
    Spinner spinner;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;

    EditText custFName, custLName, custUsername, custPassword, custRePassword, custEmail;
    String fNameText,lNameText, userNameText, passwordText, emailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_add);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        setFields();

        customerTypes = getResources().getStringArray(R.array.customerRoles);
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, customerTypes);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void AddCustomer(View view) {

        if (validate()) {
            //Upload data to database
            String user_email = custEmail.getText().toString();
            String user_password = custPassword.getText().toString();

            firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                createUser();
                                firebaseAuth.signOut();
                                Toast.makeText(AdminCustomerAdd.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminCustomerAdd.this, AdminCustomers.class));
                            } else {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(AdminCustomerAdd.this, "Registration Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }
    private Boolean validate(){

        Boolean result = false;
        fNameText = custFName.getText().toString();
        lNameText = custLName.getText().toString();
        userNameText = custUsername.getText().toString();
        passwordText = custPassword.getText().toString();
        emailText = custEmail.getText().toString();


        if(fNameText.isEmpty() || lNameText.isEmpty() || userNameText.isEmpty() || passwordText.isEmpty()
                || emailText.isEmpty())
        {
            Toast.makeText(this, "Please complete every field", Toast.LENGTH_LONG).show();

        } else{
            result = true;
        }
        return result;
    }

    private void createUser(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String custID =user.getUid();
        String custType = spinner.getSelectedItem().toString();

        Customer customer = new Customer(custID, fNameText, lNameText,userNameText,passwordText,emailText, custType);
        myRef.child("users").child(emailText).setValue(customer);
    }
    public void setFields(){
        custFName = findViewById(R.id.CustomerFName);
        custLName = findViewById(R.id.CustomerLName);
        custUsername = findViewById(R.id.CustomerUsername);
        custPassword = findViewById(R.id.CustomerPassword);
        custRePassword = findViewById(R.id.CustomerReenteredPassword);
        custEmail = findViewById(R.id.CustomerEmail);
    }

}



package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

public class AdminCustomerAdd extends AppCompatActivity {

    String[] customerTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_add);

        customerTypes = getResources().getStringArray(R.array.customerRoles);
        Spinner spinner = findViewById(R.id.spinner);
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

    public void AddCustomer(View view){
        EditText custFName = (EditText)findViewById(R.id.CustomerFName);
        EditText custLName = (EditText)findViewById(R.id.CustomerLName);
        EditText custUsername = (EditText)findViewById(R.id.CustomerUsername);
        EditText custPassword = (EditText)findViewById(R.id.CustomerPassword);
        EditText custRePassword = (EditText)findViewById(R.id.CustomerReenteredPassword);
        EditText custEmail = (EditText)findViewById(R.id.CustomerEmail);

        String txtFName = custFName.getText().toString();
        String txtLName = custLName.getText().toString();
        String txtUsername = custUsername.getText().toString();
        String txtPassword = custPassword.getText().toString();
        String txtRePassword = custRePassword.getText().toString();
        String txtEmail = custEmail.getText().toString();

        if (txtFName.matches("") || txtLName.matches("") || txtRePassword.matches("") ||
                txtUsername.matches("") || txtPassword.matches("") || txtEmail.matches("")){
            Toast.makeText(getApplicationContext(), "Please fill out the form", Toast.LENGTH_SHORT);
        }
        else {
            if (txtPassword != txtRePassword){
                Spinner spinner = findViewById(R.id.spinner);
                String spinnerResult = spinner.getSelectedItem().toString();
                AppDatabase.getAppDatabase(this).customerDao().insert(new Customer(txtFName, txtLName, txtUsername, txtPassword, txtEmail, spinnerResult));
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "The password you entered does not match", Toast.LENGTH_SHORT);
            }

        }
    }
}

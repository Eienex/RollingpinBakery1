package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Customer;
import com.rollingpinbakery.rollingpinbakery.Data.DatabaseAccess;
import com.rollingpinbakery.rollingpinbakery.Data.Product;

public class AdminCustomerEdit extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference dbReference;
    String id;
    EditText custFName, custLName, custUsername, custPassword, custEmail;
    Spinner customerRole;
    String[] customerTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_customer_edit);

        custFName = (EditText)findViewById(R.id.CustomerFName);
        custLName = (EditText)findViewById(R.id.CustomerLName);
        custUsername = (EditText)findViewById(R.id.CustomerUsername);
        custPassword = (EditText)findViewById(R.id.CustomerPassword);
        custEmail = (EditText)findViewById(R.id.CustomerEmail);
        customerRole = (Spinner)findViewById(R.id.spinner);

        id = getIntent().getStringExtra("txt_custID");
        String txt_custFName = getIntent().getStringExtra("txt_custFName");
        String txt_custLName = getIntent().getStringExtra("txt_custLName");
        String txt_username = getIntent().getStringExtra("txt_username");
        String txt_password = getIntent().getStringExtra("txt_password");
        String txt_email = getIntent().getStringExtra("txt_email");
        String txt_role = getIntent().getStringExtra("txt_role");

        //crop the strings
        txt_username = txt_username.replace("Username: ", "");
        txt_password = txt_password.replace("Password: ", "");
        txt_email = txt_email.replace("Email: ", "");
        txt_role = txt_role.replace("Role: " , "");

        //Set the text for the edittext
        custFName.setText(txt_custFName);
        custLName.setText(txt_custLName);
        custUsername.setText(txt_username);
        custPassword.setText(txt_password);
        custEmail.setText(txt_email);


        customerTypes = getResources().getStringArray(R.array.customerRoles);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, customerTypes);
        customerRole.setAdapter(arrayAdapter);
        if (!txt_role.equals(null)){
            int spinnerPosition = arrayAdapter.getPosition(txt_role);
            customerRole.setSelection(spinnerPosition);
        }
        customerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void UpdateCustomer(View view){
        EditText custFName = (EditText)findViewById(R.id.CustomerFName);
        EditText custLName = (EditText)findViewById(R.id.CustomerLName);
        EditText custUsername = (EditText)findViewById(R.id.CustomerUsername);
        EditText custPassword = (EditText)findViewById(R.id.CustomerPassword);
        final EditText custEmail = (EditText)findViewById(R.id.CustomerEmail);


        String txtFName = custFName.getText().toString();
        String txtLName = custLName.getText().toString();
        String txtUsername = custUsername.getText().toString();
        String txtPassword = custPassword.getText().toString();
        String txtEmail = custEmail.getText().toString();

        if (txtFName.matches("") || txtLName.matches("") ||
                txtUsername.matches("") || txtPassword.matches("") || txtEmail.matches("")){
            Toast.makeText(getApplicationContext(), "Please fill out the form", Toast.LENGTH_SHORT);
        }
        else {
            Spinner spinner = findViewById(R.id.spinner);
            String spinnerResult = spinner.getSelectedItem().toString();
            final Customer updatedCustomer = new Customer(id,txtFName, txtLName, txtUsername, txtPassword, txtEmail, spinnerResult);
            try{

                dbReference = FirebaseDatabase.getInstance().getReference("users");
                dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot data : dataSnapshot.getChildren())
                        {
                            Customer customer = data.getValue(Customer.class);

                            String custId = customer.get_custId();
                            if(custId.equals(updatedCustomer.get_custId())){
                                String keyID=data.getKey();

                                dbReference.child(keyID).child("custEmail").setValue(updatedCustomer.getCustEmail());
                                dbReference.child(keyID).child("custFName").setValue(updatedCustomer.getCustFName());
                                dbReference.child(keyID).child("custLName").setValue(updatedCustomer.getCustLName());
                                dbReference.child(keyID).child("custPassword").setValue(updatedCustomer.getCustPassword());
                                dbReference.child(keyID).child("custType").setValue(updatedCustomer.getCustType());
                                dbReference.child(keyID).child("custUsername").setValue(updatedCustomer.getCustUsername());
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {   }
                });
            }catch(Exception ex){
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            startActivity(new Intent(getApplicationContext(), AdminCustomers.class));
        }
    }
}

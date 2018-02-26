package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
    }

    public void SubmitRegistration(View v) {
        //Gets values
        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        EditText email = findViewById(R.id.email);
        EditText address = findViewById(R.id.address);
        EditText city = findViewById(R.id.city);
        EditText state = findViewById(R.id.state);
        EditText zip = findViewById(R.id.zipCode);

        String userNameText = userName.getText().toString();
        String passwordText = password.getText().toString();
        String emailText = email.getText().toString();
        String addressText = address.getText().toString();
        String cityText = city.getText().toString();
        String stateText = state.getText().toString();
        String zipText = zip.getText().toString();


        //Shows that values have been retrieved
        Toast.makeText(this, "Welcome " + userNameText, Toast.LENGTH_LONG).show();
    }
}


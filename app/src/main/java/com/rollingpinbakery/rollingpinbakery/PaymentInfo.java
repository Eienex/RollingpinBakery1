package com.rollingpinbakery.rollingpinbakery;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Payment;
import com.rollingpinbakery.rollingpinbakery.Data.ShippingInfo;

public class PaymentInfo extends AppCompatActivity {

    public static Intent paymentResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        //Get the previous forms extras
        Bundle extras = getIntent().getExtras();
        String ShippingName = extras.getString("ShippingName");
        String ShippingAddress = extras.getString("ShippingAddress");
        String ShippingCity = extras.getString("ShippingCity");
        String ShippingState = extras.getString("ShippingState");
        String ShippingZip = extras.getString("ShippingZip");

        paymentResults = new Intent(getApplicationContext(), OrderConfirmation.class);
        paymentResults.putExtra("ShippingName", ShippingName);
        paymentResults.putExtra("ShippingAddress", ShippingAddress);
        paymentResults.putExtra("ShippingCity",ShippingCity);
        paymentResults.putExtra("ShippingState",ShippingState);
        paymentResults.putExtra("ShippingZip", ShippingZip);
    }

    public void question(View view) {
        Toast.makeText(this, "3 digit code on back on card", Toast.LENGTH_LONG).show();
    }

    public void proceedToConfirm(View view) {

        EditText nameOnCard = (EditText)findViewById(R.id.editNameOnCard);
        EditText cardNumber = (EditText)findViewById(R.id.editCardNumber);
        Spinner cardTypeSpinner = (Spinner)findViewById(R.id.cardTypeSpinner);
        Spinner expMonthSpinner = (Spinner)findViewById(R.id.expMonth);
        Spinner expYearSpinner = (Spinner)findViewById(R.id.expYear);
        EditText csc = findViewById(R.id.editCVC);


        String nameOnCardText = nameOnCard.getText().toString();
        String cardNumberText = cardNumber.getText().toString();
        String cardTypeText = cardTypeSpinner.getSelectedItem().toString();
        String expMonthText = expMonthSpinner.getSelectedItem().toString();
        String expYearText = expYearSpinner.getSelectedItem().toString();
        String cscText = csc.getText().toString();

        if (nameOnCardText.isEmpty() || cardNumberText.isEmpty() || cardTypeText.isEmpty() ||
                expMonthText.isEmpty() || expYearText.isEmpty() || cscText.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill out the Payment form correctly", Toast.LENGTH_SHORT ).show();
        }else{
            if (cardNumberText.length() != 16){
                Toast.makeText(getApplicationContext(), "Please enter a valid Card Number", Toast.LENGTH_SHORT ).show();
            }
            else if (cscText.length() != 3){
                Toast.makeText(getApplicationContext(), "Please enter a valid CSC number. EX: 123", Toast.LENGTH_SHORT ).show();
            }
            else{
                //AppDatabase.getAppDatabase(this).paymentDao().insert(
                //new Payment(  cardTypeText, cardNumberText, nameOnCardText,
                //expMonthText+ expYearText, cscText));

                //put extras to the next form
                paymentResults.putExtra("PaymentName", nameOnCardText);
                paymentResults.putExtra("PaymentCardNumber", cardNumberText );
                paymentResults.putExtra("PaymentCardType", cardTypeText);
                paymentResults.putExtra("PaymentExpMonth", expMonthText);
                paymentResults.putExtra("PaymentExpYear", expYearText);
                paymentResults.putExtra("PaymentCSCNumber", cscText);

                startActivity(paymentResults);
            }
        }
    }
}

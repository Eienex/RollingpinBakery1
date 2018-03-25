package com.rollingpinbakery.rollingpinbakery;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);



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
        EditText csc = findViewById(R.id.csc);

        String nameOnCardText = nameOnCard.getText().toString();
        String cardNumberText = cardNumber.getText().toString();
        String cardTypeText = cardTypeSpinner.getSelectedItem().toString();
        String expMonthText = expMonthSpinner.getSelectedItem().toString();
        String expYearText = expYearSpinner.getSelectedItem().toString();
        String cscText = csc.getText().toString();



        //AppDatabase.getAppDatabase(this).paymentDao().insert(
                //new Payment(  cardTypeText, cardNumberText, nameOnCardText,
                        //expMonthText+ expYearText, cscText));

        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.OrderConfirmation"));
    }
}

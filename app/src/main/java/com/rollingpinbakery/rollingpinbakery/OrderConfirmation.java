package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView shipNameText = findViewById(R.id.ShipNameText);
        TextView shipAddressText = findViewById(R.id.ShipAddressText);
        TextView shipCityText = findViewById(R.id.ShipCityText);
        TextView shipStateText = findViewById(R.id.ShipStateText);
        TextView shipZipText = findViewById(R.id.ShipZipcodeText);

        TextView payCardNameText = findViewById(R.id.PayCardNameText);
        TextView payCardType = findViewById(R.id.cardTypeText);
        TextView payCardNumber = findViewById(R.id.CardNumText);
        TextView payCardExpText = findViewById(R.id.CardExpText);
        TextView payCardCSCText = findViewById(R.id.CardCSVNumText);

        //Get all of the form results from the shipping and payments page
        Bundle extras = getIntent().getExtras();
        String ShippingName = extras.getString("ShippingName");
        String ShippingAddress = extras.getString("ShippingAddress");
        String ShippingCity = extras.getString("ShippingCity");
        String ShippingState = extras.getString("ShippingState");
        String ShippingZip = extras.getString("ShippingZip");
        String PaymentName = extras.getString("PaymentName");
        String PaymentCardNum = extras.getString("PaymentCardNumber");
        String PaymentCardType = extras.getString("PaymentCardType");
        String PaymentExpMonth = extras.getString("PaymentExpMonth");
        String PaymentExpYear = extras.getString("PaymentExpYear");
        String PaymentCSCNumber = extras.getString("PaymentCSCNumber");



        //set the textboxes to all of the form info
        shipNameText.setText("Name: " + ShippingName);
        shipAddressText.setText("Address: " + ShippingAddress);
        shipCityText.setText("City: " + ShippingCity);
        shipStateText.setText("State: " + ShippingState);
        shipZipText.setText("Zip code: " + ShippingZip);
        payCardNameText.setText("Card Holder: " + PaymentName);
        payCardType.setText("Credit Card Type: " + PaymentCardType);
        payCardNumber.setText("Credit Card Number: " + PaymentCardNum);
        payCardExpText.setText("Expiration Date: " + PaymentExpMonth + ", " + PaymentExpYear);
        payCardCSCText.setText("Card CSC Number: " + PaymentCSCNumber);
    }

    //Method to handle the Place Order button
    public void PlaceOrder(View view){
        Toast.makeText(getApplicationContext(), "Thank you for placing an order with Rolling Pin Bakery!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}

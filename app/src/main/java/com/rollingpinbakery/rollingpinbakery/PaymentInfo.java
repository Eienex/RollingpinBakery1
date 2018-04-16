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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.AppDatabase;
import com.rollingpinbakery.rollingpinbakery.Data.Cart;
import com.rollingpinbakery.rollingpinbakery.Data.Payment;
import com.rollingpinbakery.rollingpinbakery.Data.ShippingInfo;

import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;

public class PaymentInfo extends AppCompatActivity {

    public Intent paymentResults;
    EditText nameOnCard, cardNumber, csc;
    Spinner cardTypeSpinner, expMonthSpinner, expYearSpinner;
    String nameOnCardText, cardNumberText, cscText, cardTypeText , expMonthText, expYearText;
    ArrayList<Cart> carts;
    byte[] cipherText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        nameOnCard = findViewById(R.id.editNameOnCard);
        cardNumber = findViewById(R.id.editCardNumber);
        cardTypeSpinner = findViewById(R.id.cardTypeSpinner);
        expMonthSpinner = findViewById(R.id.expMonth);
        expYearSpinner = findViewById(R.id.expYear);
        csc = findViewById(R.id.editCVC);

        carts = (ArrayList<Cart>) AppDatabase.getAppDatabase(this)
                .cartDao()
                .getAllCartItems();

        paymentResults = new Intent(getApplicationContext(), OrderConfirmation.class);
        //get all of the cart products and retrieve their values
        // and pass add them to the next Intent result
        for(int i=0; i <carts.size(); i++){

            Bundle extras = getIntent().getExtras();
            String productNum = ("Product").concat(Integer.toString(i));
            String priceNum = ("Price").concat(Integer.toString(i));
            String prodName = extras.getString(productNum);
            String prodPrice = extras.getString(priceNum);
            paymentResults.putExtra(productNum, prodName);
            paymentResults.putExtra(priceNum,prodPrice);
        }

        //Get the previous forms extras
        Bundle extras = getIntent().getExtras();
        String ShippingName = extras.getString("ShippingName");
        String ShippingAddress = extras.getString("ShippingAddress");
        String ShippingCity = extras.getString("ShippingCity");
        String ShippingState = extras.getString("ShippingState");
        String ShippingZip = extras.getString("ShippingZip");


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
        if(validate()){
            if(validate()){
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                String custID =user.getUid();
                String paymentID = myRef.push().getKey();
                String expDate = expMonthText + "/" + expYearText;

                Payment paymentInfo = new Payment(paymentID,custID, cardTypeText, cardNumberText, nameOnCardText, expDate, cscText);
                myRef.child("Payments").child(paymentID).setValue(paymentInfo);
                startActivity(paymentResults);
            }
            // AppDatabase.getAppDatabase(this).shippingInfoDao().insert(
            //     new ShippingInfo(nameText, addressText, cityText,
            //               stateText, zipCodeText));
        }

    }
    private Boolean validate(){

        Boolean result = false;

       nameOnCardText = nameOnCard.getText().toString();
       cardNumberText = cardNumber.getText().toString();
       cardTypeText = cardTypeSpinner.getSelectedItem().toString();
       expMonthText = expMonthSpinner.getSelectedItem().toString();
       expYearText = expYearSpinner.getSelectedItem().toString();
       cscText = csc.getText().toString();

        if (nameOnCardText.isEmpty() || cardNumberText.isEmpty() || cardTypeText.isEmpty() ||
                expMonthText.isEmpty() || expYearText.isEmpty() || cscText.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill out the Payment form correctly", Toast.LENGTH_SHORT ).show();
        }else {
            if (cardNumberText.length() != 16) {
                Toast.makeText(getApplicationContext(), "Please enter a valid Card Number", Toast.LENGTH_SHORT).show();
            } else if (cscText.length() != 3) {
                Toast.makeText(getApplicationContext(), "Please enter a valid CSC number. EX: 123", Toast.LENGTH_SHORT).show();
            } else {
                //AppDatabase.getAppDatabase(this).paymentDao().insert(
                //new Payment(  cardTypeText, cardNumberText, nameOnCardText,
                //expMonthText+ expYearText, cscText));

                //put extras to the next form
                try {


                    SecureRandom random = new SecureRandom();
                    IvParameterSpec ivSpec = AndroidCryptUtils.createCtrIvForAES(1, random);
                    //KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
                    //Generator.initialize(534, random);
                    //KeyPair pair = generator.generateKeyPair();
                    Key key = AndroidCryptUtils.createKeyForAES(256, random);
                    //Key privKey = pair.getPrivate();
                    Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
                    //encryption
                    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
                    cipherText = cipher.doFinal(AndroidCryptUtils.toByteArray(cardNumberText));

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                paymentResults.putExtra("PaymentName", nameOnCardText);
                paymentResults.putExtra("PaymentCardNumber", cipherText);
                paymentResults.putExtra("PaymentCardType", cardTypeText);
                paymentResults.putExtra("PaymentExpMonth", expMonthText);
                paymentResults.putExtra("PaymentExpYear", expYearText);
                paymentResults.putExtra("PaymentCSCNumber", cscText);
            }

                result = true;
        }
        return result;
    }

}

package com.rollingpinbakery.rollingpinbakery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.util.List;

public class Register extends AppCompatActivity {

    private EditText fName, lName, userName, password, email;
    private TextView login;
    private Button regBtn;

    private FirebaseAuth firebaseAuth;
    String fNameText,lNameText, userNameText, passwordText, emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        setUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to database
                    String user_email = email.getText().toString();
                    String user_password = password.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        createUser();
                                        firebaseAuth.signOut();
                                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Register.this, LoginActivity.class));
                                    }else{
                                        FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                        Toast.makeText(Register.this, "Registration Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                try{

                    String fNameText = fName.getText().toString();
                    String lNameText = lName.getText().toString();
                    String userNameText = userName.getText().toString();
                    String passwordText = password.getText().toString();
                    String emailText = email.getText().toString();

                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Register.this);
                    databaseAccess.open();
                    //AppDatabase.getAppDatabase(this).customerDao().insert(new Customer(fNameText, lNameText, userNameText, passwordText, emailText, "Customer"));
                    databaseAccess.insertCustomer(new Customer(fNameText, lNameText, userNameText, passwordText, emailText, "Customer"));
                    Toast.makeText(Register.this, "Welcome to the Rolling Pin Bakery, " + fNameText, Toast.LENGTH_LONG).show();
                    databaseAccess.close();
                    Register.this.finish();
                    startActivity(new Intent(Register.this, LoginActivity.class));

                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, LoginActivity.class));
            }
        });
    }

    private void setUIViews(){
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        regBtn = findViewById(R.id.regBtn);
        login = findViewById(R.id.login);


    }

    private Boolean validate(){

        Boolean result = false;
        fNameText = fName.getText().toString();
        lNameText = lName.getText().toString();
        userNameText = userName.getText().toString();
        passwordText = password.getText().toString();
        emailText = email.getText().toString();


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
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String custType = "Customer";
        String custID =user.getUid();
        Customer customer = new Customer(custID, fNameText, lNameText,userNameText,passwordText,emailText, custType);
        myRef.setValue(customer);
    }
    public void SubmitRegistration(View v) {
        //Gets values
        try {

            fName = findViewById(R.id.fName);
            lName = findViewById(R.id.lName);
            userName = findViewById(R.id.userName);
            password = findViewById(R.id.password);
            email = findViewById(R.id.email);

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


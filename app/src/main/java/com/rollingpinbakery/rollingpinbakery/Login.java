package com.rollingpinbakery.rollingpinbakery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity{

    private EditText email, password;
    private TextView signUp;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        signUp = findViewById(R.id.signUp);
        login = findViewById(R.id.button);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(Login.this, MainActivity.class));
        }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }

    private void validate(String email, String password){
        progressDialog.setMessage("Please wait while we verify your email and password");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void Submit(View view){
        validate(email.getText().toString(), password.getText().toString());
    }
    public void passReset(View v){
        startActivity(new Intent("com.rollingpinbakery.rollingpinbakery.PasswordReset"));
    }
}

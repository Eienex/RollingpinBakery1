package com.rollingpinbakery.rollingpinbakery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class PasswordReset extends AppCompatActivity {

    private EditText inputEmail;
    private TextView resetButton, backButton;
    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        inputEmail = findViewById(R.id.email);
        resetButton = findViewById(R.id.resetButton);
        backButton = findViewById(R.id.backBtn);
        progressBar = findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = inputEmail.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplication(), "Enter your registered email", Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(PasswordReset.this, "We have sent you instructions to reset your password!", Toast.LENGTH_LONG).show();
                                } else{
                                    Toast.makeText(PasswordReset.this, "Failed to send reset email", Toast.LENGTH_LONG).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });


    }
}

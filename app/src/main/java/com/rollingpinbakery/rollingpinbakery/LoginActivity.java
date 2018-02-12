package com.rollingpinbakery.rollingpinbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Submit(View v){
        //Gets values
        EditText e = findViewById(R.id.editText);
        String test1 = e.getText().toString();
        EditText f = findViewById(R.id.editText2);
        String test2 = f.getText().toString();

        //Shows that values have been retrieved
        Toast.makeText(getApplicationContext(), test1 + " " + test2,Toast.LENGTH_SHORT).show();

    }

}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    boolean validationFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Validate();
                if(validationFlag){
                    Intent intent = new Intent(MainActivity.this, MainMenu1.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void Validate(){

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        String strUsername = username.getText().toString();
        String strPassword = password.getText().toString();

        validationFlag = false;
        if(!strUsername.isEmpty() && !strPassword.isEmpty()) {
            if(strUsername.equals("Admin") && strPassword.equals("Admin")) {
                validationFlag = true;
            }
        }

        if(!validationFlag) {
            Toast.makeText(MainActivity.this,"Invalid Login!",
                    Toast.LENGTH_LONG).show();
        }

    }
}
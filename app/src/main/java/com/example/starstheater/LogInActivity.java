package com.example.starstheater;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;


import java.util.Objects;

import entities.Database;

public class LogInActivity extends AppCompatActivity {

    private AppCompatEditText etEmail,etPassword;
    private AppCompatButton login;
    private TextView guestLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initialize();
        setLoginButton();
        setGuestLogin();

    }

    private void initialize(){
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        guestLogin = findViewById(R.id.tv_guest_enter);
    }
    private void setLoginButton(){

        login.setOnClickListener(view -> {
            String email = Objects.requireNonNull(etEmail.getText()).toString();
            String password = Objects.requireNonNull(etPassword.getText()).toString();
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(getBaseContext(),"Fill All Fields",Toast.LENGTH_SHORT).show();
            }
            else{
                Database database = new Database(getBaseContext());
                database.signInUser(email,password);
            }
        });
    }
    private void setGuestLogin(){
        guestLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(),HomeActivity.class);
            startActivity(intent);
        });
    }
}
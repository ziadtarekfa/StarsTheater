package com.example.starstheater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class ReservationCompletedActivity extends AppCompatActivity {

    AppCompatButton backToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_completed);
        backToHome = findViewById(R.id.back_to_home_btn);


        backToHome.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(),HomeActivity.class);
            startActivity(intent);
        });
    }
}
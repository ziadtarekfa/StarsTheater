package com.example.starstheater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import entities.FourDigitCardFormatWatcher;
import entities.Movie;
import entities.MovieProfileDate;

public class PaymentActivity extends AppCompatActivity {

    TextView npOrder, movieName, session, seats, price, showTimeAndDate;
    AppCompatButton payNow;
    AutoCompleteTextView autoCompleteTxtMonths,autoCompleteTxtYears;
    ArrayAdapter<String> adapterMonths;
    ArrayAdapter<String> adapterYears;
    AppCompatEditText creditCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        MovieProfileDate reservedDate = (MovieProfileDate) getIntent().getSerializableExtra("date");
        String reservedSeats = getIntent().getStringExtra("reservedSeats");
        String reservedTime = getIntent().getStringExtra("reservedTime");

        String [] items = getResources().getStringArray(R.array.months);
        String [] yearItems = getResources().getStringArray(R.array.years);
        npOrder = findViewById(R.id.tv_np_order);
        movieName = findViewById(R.id.tv_movie_info);
        session = findViewById(R.id.tv_session);
        seats = findViewById(R.id.tv_seats);
       price = findViewById(R.id.tv_total_price);
        showTimeAndDate = findViewById(R.id.tv_test);
        payNow = findViewById(R.id.btn_pay_now);
        autoCompleteTxtMonths = findViewById(R.id.autoCompleteTextView1);
        autoCompleteTxtYears = findViewById(R.id.autoCompleteTextView2);
        creditCard = findViewById(R.id.credit_card_no);

        creditCard.addTextChangedListener(new FourDigitCardFormatWatcher());
        adapterMonths = new ArrayAdapter<String>(this,R.layout.drop_down_item,items);
        adapterYears = new ArrayAdapter<String>(this,R.layout.drop_down_item,yearItems);
        autoCompleteTxtMonths.setAdapter(adapterMonths);
        autoCompleteTxtYears.setAdapter(adapterYears);


        npOrder.setText(movie.getNpOrder());
        movieName.setText(movie.getName());
        session.setText(reservedTime + "," + reservedDate.getDay()+ " " + reservedDate.getMonth());
        seats.setText(reservedSeats);
      //  price.setText(movie.getPrice());
        showTimeAndDate.setText(reservedDate.getDay() + " " + reservedDate.getMonth() + "|" + reservedTime);


        payNow.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), ReservationCompletedActivity.class);
            startActivity(intent);
        });













    }
}
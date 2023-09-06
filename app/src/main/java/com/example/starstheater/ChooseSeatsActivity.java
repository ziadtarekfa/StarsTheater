package com.example.starstheater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import adapters.TimeSliderAdapter;
import entities.Movie;
import entities.MovieProfileDate;

public class ChooseSeatsActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    AppCompatButton checkout;
    TextView movieName, totalTicketPrice , noOfSeatsSelected;
    TableLayout layout;
    ImageView g1,g2,g3;
 //   Seat[] seats = new Seat[56];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seats);
        viewPager = findViewById(R.id.viewPager2);
        movieName = findViewById(R.id.tv_movie_name_choose_seats);
        totalTicketPrice = findViewById(R.id.tv_moive_price_choose_seats);
        checkout = findViewById(R.id.btn_checkout_choose_activity);
        layout = findViewById(R.id.table_layout);
        noOfSeatsSelected = findViewById(R.id.tv_no_of_seats_choose_activity);
        g1 = findViewById(R.id.seat_g1);
        g2 = findViewById(R.id.seat_g2);
        g3 = findViewById(R.id.seat_g3);


        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        MovieProfileDate date = (MovieProfileDate) getIntent().getSerializableExtra("date");


        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g1.setImageResource(R.drawable.ic_selected);
                totalTicketPrice.setText("120LE");
                noOfSeatsSelected.setText("1 Seat Selected");
            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g2.setImageResource(R.drawable.ic_selected);
                totalTicketPrice.setText("240LE");
                noOfSeatsSelected.setText("2 Seats Selected");
            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g3.setImageResource(R.drawable.ic_selected);
                totalTicketPrice.setText("480LE");
                noOfSeatsSelected.setText("3 Seats Selected");
            }
        });


//        TableRow row = null; ;
//        for (int i = 0 ; i < seats.length ; i++){
//
//            if (i % 8 == 0){
//                row = new TableRow(this);
//                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//            }
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(R.drawable.ic_reserved);
//
//            row.addView(imageView);
//
//            if(i % 8 == 0 ){
//                layout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//            }
//
//        }

        String reservedSeats = "G1,G2,G3";
        String reservedTime = "2:30pm";

        viewPager.setAdapter(new TimeSliderAdapter());

        movieName.setText(movie.getName());
//        totalTicketPrice.setText(movie.getPrice());

        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);



        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();

        compositePageTransformer.addTransformer((page, position) -> {

            if (position == -1 )  {
                //left side
                page.setTranslationX(540);
                page.setTranslationY(40);
                page.setAlpha(0.3f);

            }
            else if (position == 0){
                //center
                page.setTranslationX(0);
                page.setTranslationY(0);
                page.setAlpha(1);
            }
 else if (position == 1) {
               //right side
                page.setTranslationX(-540);
                page.setTranslationY(40);
                page.setAlpha(0.3f);

            }

        });



        viewPager.setPageTransformer(compositePageTransformer);



        checkout.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), PaymentActivity.class);
            intent.putExtra("movie",movie);
            intent.putExtra("date",date);
            intent.putExtra("reservedSeats",reservedSeats);
            intent.putExtra("reservedTime",reservedTime);

            startActivity(intent);
        });




    }
}
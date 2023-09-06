package com.example.starstheater;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.text.ParseException;

import entities.MyDate;
import entities.Ticket;

public class ViewTicketActivity extends AppCompatActivity {


    private TextView name, npOrder, movieDate, seats, price, time, movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);
        Ticket ticket = (Ticket) getIntent().getSerializableExtra("ticket");
        initialize();
        try {
            setTexts(ticket);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initialize(){
        name = findViewById(R.id.ticket_owner);
        npOrder = findViewById(R.id.np_order);
        movieDate = findViewById(R.id.movie_date);
        seats = findViewById(R.id.ticket_seats);
        price = findViewById(R.id.ticket_price);
        time = findViewById(R.id.movie_time);
        movieName = findViewById(R.id.movie_name_ticket);
    }
    private void setTexts(Ticket ticket) throws ParseException {
        name.setText(ticket.getOwner());
        npOrder.setText(ticket.getNpOrder());
        movieName.setText(ticket.getMovieName());
        seats.setText(ticket.getSeats());
        price.setText(ticket.getPrice());
        time.setText(ticket.getMovieTime());

        MyDate myDate = new MyDate(ticket.getMovieDate());
        movieDate.setText(myDate.getDateForTicketDetails());
    }


}
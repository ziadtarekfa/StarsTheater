package com.example.starstheater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import adapters.CastRecyclerViewAdapter;
import adapters.DateRecyclerViewAdapter;
import entities.Cast;
import entities.Movie;
import entities.MovieProfileDate;

public class MovieProfileActivity extends AppCompatActivity {

    private RecyclerView rvCast ,rvDate;
    private TextView name,type,rating,duration,synopsis;
    private final ArrayList<Cast> castArrayList = new ArrayList<>();
    private Movie movie;
    private ArrayList<MovieProfileDate> movieDates = new ArrayList<>() ;
    private final CastRecyclerViewAdapter adapter = new CastRecyclerViewAdapter(castArrayList,getBaseContext());
    private  DateRecyclerViewAdapter dateAdapter;
    private AppCompatButton reservation;
    private MovieProfileDate dateSelected;
    private Calendar calendar =  Calendar.getInstance();
    private LinearLayout layout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_profile);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        initialize();
        synopsis.setMovementMethod(new ScrollingMovementMethod());
        setTextViews();
        getCast();
        rvCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rvCast.setAdapter(adapter);


        for (int i = 0 ; i < 30 ; i++){
            String month = new SimpleDateFormat("MMMM",Locale.getDefault()).format(calendar.getTime());
            String day = new SimpleDateFormat("dd",Locale.getDefault()).format(calendar.getTime());
            movieDates.add(new MovieProfileDate(month,day));
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }



        dateAdapter = new DateRecyclerViewAdapter(movieDates,movie,reservation);

        rvDate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvDate.setAdapter(dateAdapter);
    }



    private void initialize(){
        name = findViewById(R.id.tv_movie_name);
        type = findViewById(R.id.tv_movie_type);
        rating = findViewById(R.id.tv_rating);
        duration = findViewById(R.id.tv_movie_duration);
        synopsis = findViewById(R.id.tv_synopsis);
        rvCast = findViewById(R.id.rv_cast);
        rvDate = findViewById(R.id.rv_dates);
        layout = findViewById(R.id.date_linear_layout);
        reservation = findViewById(R.id.btn_reservation);
    }
    private void setTextViews(){
        name.setText(movie.getName());
        type.setText(movie.getType());
        rating.setText(movie.getRating());
        duration.setText(movie.getDuration());
        synopsis.setText(movie.getSynopsis());

    }
    private void getCast(){

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Movies").child(movie.getId()).child("cast");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    Cast cast = postSnapshot.getValue(Cast.class);
                    assert cast != null;
                    castArrayList.add(cast);
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MovieProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starstheater.ChooseSeatsActivity;
import com.example.starstheater.R;
import com.example.starstheater.TicketsRecyclerViewListener;

import java.util.ArrayList;

import entities.Movie;
import entities.MovieProfileDate;

public  class DateRecyclerViewAdapter extends RecyclerView.Adapter<DateRecyclerViewAdapter.DateViewHolder> {

    ArrayList<MovieProfileDate> dates;
    AppCompatButton btn;
    Movie movie;
    boolean clicked = false;
//    TicketsRecyclerViewListener listener;
    int row_index = -1;


    public DateRecyclerViewAdapter(ArrayList<MovieProfileDate> dates, Movie movie,AppCompatButton  btn) {
        this.dates = dates;
        this.movie = movie;
        this.btn = btn;

//        this.listener = listener;
    }

    @NonNull
    @Override
    public DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_date_item,parent,false);
        return new DateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHolder holder, @SuppressLint("RecyclerView") int position ) {
        holder.month.setText(dates.get(position).getMonth());
        holder.day.setText(dates.get(position).getDay());




        holder.layout.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();


        });

        if(row_index==position){
            //clicked
            holder.layout.setBackgroundResource(R.color.red);
            clicked = true;
        }
        else
        {
            holder.layout.setBackgroundResource(R.color.date_color);

        }

        btn.setOnClickListener(view -> {
            if(!clicked){
                Toast.makeText(holder.itemView.getContext(),"Choose a date please",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(holder.itemView.getContext(), ChooseSeatsActivity.class);
                intent.putExtra("movie",movie);
                intent.putExtra("date",dates.get(row_index));

                holder.itemView.getContext().startActivity(intent);
       }

        });


    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

     static class DateViewHolder extends RecyclerView.ViewHolder {
         TextView day , month;
         CardView cardView;
         LinearLayout layout;
        public DateViewHolder(@NonNull View itemView)  {
            super(itemView);
            day = itemView.findViewById(R.id.tv_day);
            month = itemView.findViewById(R.id.tv_month);
            cardView = itemView.findViewById(R.id.my_card);
            layout = itemView.findViewById(R.id.date_linear_layout);
//            itemView.setOnClickListener(this);
        }
//
//        @Override
//        public void onClick(View view) {
//          //  listener.onClick(view,getAdapterPosition());
//        }
    }
}

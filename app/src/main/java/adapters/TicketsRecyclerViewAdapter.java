package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starstheater.R;
import com.example.starstheater.TicketsRecyclerViewListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;

import entities.Ticket;

public class TicketsRecyclerViewAdapter extends RecyclerView.Adapter<TicketsRecyclerViewAdapter.TicketsViewHolder> {

    ArrayList<Ticket> tickets;
    Context context;
    TicketsRecyclerViewListener listener;

    public TicketsRecyclerViewAdapter(ArrayList<Ticket> tickets, Context context, TicketsRecyclerViewListener listener) {
        this.tickets = tickets;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ticket_item,parent,false);
        return new TicketsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder holder, int position)   {

        Ticket ticket = tickets.get(position);
        Picasso.with(context).load(ticket.getMovieURL()).into(holder.moviePic);
        holder.movieName.setText(ticket.getMovieName());
        try {
            holder.movieTimeAndDate.setText(ticket.getMovieTimeAndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.noOfSeats.setText(ticket.getNoOfSeats());




    }



    @Override
    public int getItemCount() {
        return tickets.size();
    }


    class TicketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView moviePic;
        TextView movieName, movieTimeAndDate,noOfSeats;
        AppCompatButton viewTicket;
        public TicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePic = itemView.findViewById(R.id.movie_pic);
            movieName = itemView.findViewById(R.id.movie_name);
            movieTimeAndDate = itemView.findViewById(R.id.movie_time_date);
            noOfSeats = itemView.findViewById(R.id.no_of_seats);
            viewTicket = itemView.findViewById(R.id.btn_view_ticket);
            viewTicket.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

}

package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.starstheater.R;
import com.example.starstheater.ViewTicketActivity;
import java.util.ArrayList;

import adapters.TicketsRecyclerViewAdapter;
import entities.Ticket;



public class TicketsFragment extends Fragment {


    TicketsRecyclerViewAdapter todayTicketsAdapter,upcomingTicketsAdapter;
    RecyclerView rv_todayTickets, rv_upcomingTickets;
    TextView tvUpcomingTickets,tvTodayTickets , noTickets;
    ArrayList<Ticket> todayTickets;
    ArrayList<Ticket> upcomingTickets;

    public TicketsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        assert getArguments() != null;
         todayTickets = (ArrayList<Ticket>) getArguments().getSerializable("todayTickets");
         upcomingTickets = (ArrayList<Ticket>) getArguments().getSerializable("upcomingTickets");

         View v = inflater.inflate(R.layout.fragment_tickets, container, false);
         initialize(v);

        if(todayTickets.isEmpty() && upcomingTickets.isEmpty()){
            tvTodayTickets.setVisibility(View.GONE);
            rv_todayTickets.setVisibility(View.GONE);
            tvUpcomingTickets.setVisibility(View.GONE);
            rv_upcomingTickets.setVisibility(View.GONE);
            noTickets.setVisibility(View.VISIBLE);

        }
        else if (todayTickets.isEmpty()){
            tvTodayTickets.setVisibility(View.GONE);
            rv_todayTickets.setVisibility(View.GONE);
            handleUpcomingTicketsAdapter(v);
        }
        else if (upcomingTickets.isEmpty()){
            tvUpcomingTickets.setVisibility(View.GONE);
            rv_upcomingTickets.setVisibility(View.GONE);
            handleTodayTicketsAdapter(v);
        }
        else{
            //both have tickets
            handleTodayTicketsAdapter(v);
            handleUpcomingTicketsAdapter(v);

        }
           return v;
    }
    private void initialize(View v){
        rv_todayTickets = v.findViewById(R.id.rv_today_tickets);
        rv_upcomingTickets = v.findViewById(R.id.rv_upcoming_tickets);
        tvUpcomingTickets = v.findViewById(R.id.tv_upcoming_tickets);
        tvTodayTickets = v.findViewById(R.id.tv_today_tickets);
        noTickets = v.findViewById(R.id.tv_no_tickets);
    }

    private void handleTodayTicketsAdapter(View v){
        todayTicketsAdapter = new TicketsRecyclerViewAdapter(todayTickets, v.getContext(), (v1, position) -> {
            Intent intent = new Intent(v1.getContext(), ViewTicketActivity.class);
            intent.putExtra("ticket", todayTickets.get(position));
            startActivity(intent);

        });
        rv_todayTickets.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rv_todayTickets.setAdapter(todayTicketsAdapter);
    }

    private void handleUpcomingTicketsAdapter(View v){
        upcomingTicketsAdapter = new TicketsRecyclerViewAdapter(upcomingTickets, v.getContext(), (v1, position) -> {
            Intent intent = new Intent(v1.getContext(),ViewTicketActivity.class);
            intent.putExtra("ticket", upcomingTickets.get(position));
            startActivity(intent);

        });
        rv_upcomingTickets.setLayoutManager(new LinearLayoutManager(v.getContext()));
        rv_upcomingTickets.setAdapter(upcomingTicketsAdapter);

    }

}
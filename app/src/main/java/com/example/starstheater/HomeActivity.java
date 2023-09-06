package com.example.starstheater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import entities.Database;
import entities.Ticket;
import entities.User;
import fragments.HomeFragment;
import fragments.SearchFragment;
import fragments.TicketsFragment;


public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private TextView userName;
    private CircleImageView userProfilePic;
    private final String userID = FirebaseAuth.getInstance().getUid();
    private final ArrayList<Ticket> todayTickets = new ArrayList<>();
    private final ArrayList<Ticket> upcomingTickets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialize();
        getTickets();
        handleNavigation();
        setBottomNavigationView();
        setSideNavigationActions();


    }

    private void initialize(){
        navigationView = findViewById(R.id.navigationView);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        View headerView = navigationView.getHeaderView(0);
        userName = headerView.findViewById(R.id.nav_header_user_name);
        userProfilePic = headerView.findViewById(R.id.nav_header_user_pic);
    }

    private void handleNavigation(){
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                assert user != null;
                userName.setText(user.getName());
                Picasso.with(getBaseContext()).load(user.getProfilePicURL()).into(userProfilePic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @SuppressLint("NonConstantResourceId")
    private void setBottomNavigationView(){

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.btm_nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.btm_nav_tickets:
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("todayTickets",todayTickets);
                    bundle.putSerializable("upcomingTickets",upcomingTickets);
                    Fragment ticketsFragment = new TicketsFragment();
                    ticketsFragment.setArguments(bundle);
                    replaceFragment(ticketsFragment);
                    break;
                case  R.id.btm_nav_search:
                    replaceFragment(new SearchFragment());
                    break;
            }

            return true;
        });

    }

    private void setSideNavigationActions(){
        navigationView.setNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.side_nav_logout){
                Database database = new Database(getBaseContext());
                database.signOutUser();
            }
            return true;
        });
    }

    private void getTickets(){
        DatabaseReference ticketsRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("tickets");

        ticketsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren() ){
                    Ticket ticket = postSnapshot.getValue(Ticket.class);
                    assert ticket != null;

                    try {
                        Date movieDate = new SimpleDateFormat("yyyy/MM/dd").parse(ticket.getMovieDate());
                        Date currentDate = new Date();

                        assert movieDate != null;
                        if (movieDate.after(currentDate)){
                            Log.d("TicketsFragment", "Date is after");
                            upcomingTickets.add(ticket);
                        }
                        else {
                            Log.d("TicketsFragment", "Today's Date");
                            todayTickets.add(ticket);
                        }


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        menu = bottomNavigationView.getMenu();
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }
}
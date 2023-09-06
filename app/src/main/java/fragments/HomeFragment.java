package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.example.starstheater.MovieProfileActivity;
import com.example.starstheater.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapters.PosterSliderAdapter;
import entities.Movie;


public class HomeFragment extends Fragment {

    private ViewPager2 imagesViewPager;
    private Handler handler = new Handler();

    private final ArrayList <Movie> movies = new ArrayList<>();


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

      //  TextView movieName = v.findViewById(R.id.tv_movie_name);

        imagesViewPager = v.findViewById(R.id.image_slider);
        getMovies(v);
        setSliderProperties();
        setSliderTransformers();


        imagesViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);

            }
        });






        return v;

    }



    private void setSliderProperties(){

        imagesViewPager.setClipToPadding(false);
        imagesViewPager.setClipChildren(false);
        imagesViewPager.setOffscreenPageLimit(3);

    }

    private void setSliderTransformers(){

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(80));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1- Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);


            if (position == -1 )  {
                //left side
                page.setTranslationX(120);
                page.setTranslationY(60);
                page.setRotation(-5);

            }
            else if (position == 0){
                //center
                page.setRotation(0);
            }
            else if (position == 1){
                //right side
                page.setTranslationX(-120);
                page.setTranslationY(60);
                page.setRotation(5);

            }

        });






        imagesViewPager.setPageTransformer(compositePageTransformer);
    }


    private void getMovies (View v) {

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Movies");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            Movie movie;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    movie = postSnapshot.getValue(Movie.class);
                    movies.add(movie);

                }
                imagesViewPager.setAdapter(new PosterSliderAdapter(v.getContext(), movies, imagesViewPager , (v1, position) -> {
                    Intent intent = new Intent(v1.getContext(), MovieProfileActivity.class);
                    intent.putExtra("movie",movies.get(position));
                    startActivity(intent);
                }));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(v.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imagesViewPager.setCurrentItem(imagesViewPager.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
}
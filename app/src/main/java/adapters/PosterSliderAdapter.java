package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.starstheater.R;
import com.example.starstheater.TicketsRecyclerViewListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import entities.Movie;

public class PosterSliderAdapter extends RecyclerView.Adapter<PosterSliderAdapter.SliderViewHolder> {

    private final Context context;
    private final ArrayList<Movie> movies;
    private ViewPager2 viewPager2;
    TicketsRecyclerViewListener listener;


    public PosterSliderAdapter(Context context, ArrayList<Movie> movies, ViewPager2 viewPager2, TicketsRecyclerViewListener listener) {
        this.context = context;
        this.movies = movies;
        this.viewPager2 = viewPager2;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,parent,false);
        return new SliderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Picasso.with(context).load(movies.get(position).getImageURL()).into(holder.imageView);
        if (position == movies.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.my_image);
            imageView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            movies.addAll(movies);
            notifyDataSetChanged();
        }
    };
}

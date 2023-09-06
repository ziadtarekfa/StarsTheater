package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starstheater.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import entities.Cast;

public class CastRecyclerViewAdapter extends RecyclerView.Adapter<CastRecyclerViewAdapter.CastViewHolder> {

    private ArrayList<Cast> cast;
    private Context context;

    public CastRecyclerViewAdapter(ArrayList<Cast> cast, Context context) {
        this.cast = cast;
        this.context = context;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cast_item,parent,false);
        return new CastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast myCast = cast.get(position);
        Picasso.with(context).load(myCast.getImageURL()).into(holder.imageView);
        holder.name.setText(myCast.getName());
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    static class  CastViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView imageView;
        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_cast_name);
            imageView = itemView.findViewById(R.id.cast_image);
        }
    }
}

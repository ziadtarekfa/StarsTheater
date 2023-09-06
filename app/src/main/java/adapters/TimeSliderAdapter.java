package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starstheater.R;

import java.util.ArrayList;

public class TimeSliderAdapter extends RecyclerView.Adapter<TimeSliderAdapter.SliderViewHolder> {

    // private final Context context;
    private ArrayList<String> arrayList = new ArrayList<>();

    public TimeSliderAdapter() {
        arrayList.add("1:30pm");
        arrayList.add("2:30pm");
        arrayList.add("3:30pm");
        arrayList.add("4:30pm");
        arrayList.add("5:30pm");
        arrayList.add("6:30pm");
        arrayList.add("7:30pm");
        arrayList.add("8:30");
        arrayList.add("9:30");
        arrayList.add("10:30");
        arrayList.add("11:30");
        arrayList.add("12:30");
    }


    @NonNull
    @Override
    public TimeSliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_time_item, parent, false);
        return new TimeSliderAdapter.SliderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeSliderAdapter.SliderViewHolder holder, int position) {
        holder.showTime.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class SliderViewHolder extends RecyclerView.ViewHolder {
        TextView showTime;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            showTime = itemView.findViewById(R.id.tv_time);
        }


    }
}
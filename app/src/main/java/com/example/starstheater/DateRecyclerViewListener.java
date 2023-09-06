package com.example.starstheater;

import android.view.MotionEvent;
import android.view.View;

public interface DateRecyclerViewListener {
    void onClick(View v, MotionEvent event, int position);
}

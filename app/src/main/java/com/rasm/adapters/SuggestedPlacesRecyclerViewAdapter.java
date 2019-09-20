package com.rasm.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rasm.R;
import com.rasm.adventures.Place;

import java.util.ArrayList;

public class SuggestedPlacesRecyclerViewAdapter {

    private ArrayList<Place> places = new ArrayList<>();
    private Context mContext;

    public SuggestedPlacesRecyclerViewAdapter(){

    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.suggested_place_image);
            title = itemView.findViewById(R.id.suggested_place_title);
            cardView = itemView.findViewById(R.id.suggested_place_card);
        }
    }
}

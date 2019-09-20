package com.rasm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rasm.R;
import com.rasm.adventures.Place;

import java.util.ArrayList;

public class SuggestedPlacesRecyclerViewAdapter extends
        RecyclerView.Adapter<SuggestedPlacesRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Place> places = new ArrayList<>();
    private Context mContext;

    public SuggestedPlacesRecyclerViewAdapter(Context mContext, ArrayList<Place> places) {
        this.places = places;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested_places_item,
                parent, false);
        return new SuggestedPlacesRecyclerViewAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(places.get(position).getImages().get(0))
                .into(holder.image);

        holder.title.setText(places.get(position).getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //if clicked go to detailed place activity

            }
        });

    }

    @Override
    public int getItemCount() {
        return places.size();
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

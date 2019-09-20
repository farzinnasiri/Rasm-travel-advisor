package com.rasm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rasm.ExploreAdventuresActivity;
import com.rasm.R;

import java.util.ArrayList;
import java.util.List;

public class OptionsRecyclerViewAdapter extends RecyclerView.Adapter<OptionsRecyclerViewAdapter
        .ViewHolder> {

    private static final String TAG = "NewFriendsAdventureAdapter";

    //vars
    private List<String> titles = new ArrayList<>();
    private List<Integer> optionsImages = new ArrayList<>();
    private Context mContext;

    public OptionsRecyclerViewAdapter(Context context, ArrayList<String> titles, ArrayList<Integer>
            optionsImages) {
        this.titles = titles;
        this.optionsImages = optionsImages;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.options_list_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        Glide.with(mContext)
                .asBitmap()
                .load(optionsImages.get(position))
                .into(holder.image);

        holder.name.setText(titles.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titles.get(position).equals("ماجراجویی")){
                    Intent intent = new Intent(mContext, ExploreAdventuresActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionsImages.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.option_image);
            name = itemView.findViewById(R.id.option_title);
            cardView = itemView.findViewById(R.id.option_card);
        }
    }
}

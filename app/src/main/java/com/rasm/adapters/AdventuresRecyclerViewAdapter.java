package com.rasm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rasm.R;
import com.rasm.adventures.Adventure;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdventuresRecyclerViewAdapter extends
        RecyclerView.Adapter<AdventuresRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Adventure> adventures;
    private Context mContext;

    public AdventuresRecyclerViewAdapter(Context mContext, ArrayList<Adventure> adventures){
        this.adventures = adventures;
        this.mContext = mContext;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adventure_item,
                parent, false);
        return new AdventuresRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.username.setText(adventures.get(position).getUsername());

//        viewHolder.title.setText(adventures.get(position).get);
        viewHolder.desc.setText(adventures.get(position).getDescriptions());
//        viewHolder.date.setText(adventures.get(position).);
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });




    }

    @Override
    public int getItemCount()
    {
        return adventures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        TextView title;
        TextView username;
        TextView date;
        TextView desc;
        CircleImageView profilePhoto;



    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        card = itemView.findViewById(R.id.adventure_card);
        date = itemView.findViewById(R.id.adventure_card);
        username = itemView.findViewById(R.id.adventure_card);
        title = itemView.findViewById(R.id.adventure_card);
        desc = itemView.findViewById(R.id.adventure_desc);
        profilePhoto = itemView.findViewById(R.id.adventure_card_profile_photo);
    }
}

}

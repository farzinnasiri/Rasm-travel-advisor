package com.rasm.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rasm.R;
import com.rasm.adventures.Adventure;

import java.util.ArrayList;

public class AdventuresRecylerViewAdapter extends
        RecyclerView.Adapter<AdventuresRecylerViewAdapter.ViewHolder> {

    private ArrayList<Adventure> adventures;
    private Context mContext;

    public AdventuresRecylerViewAdapter(Context mContext, ArrayList<Adventure> adventures){


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adventure_item,
                parent, false);
        return new AdventuresRecylerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int positon) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

}

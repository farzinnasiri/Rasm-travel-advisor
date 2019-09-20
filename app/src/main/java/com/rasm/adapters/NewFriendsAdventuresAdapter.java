package com.rasm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rasm.Administering.Administer;
import com.rasm.Administering.User;
import com.rasm.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewFriendsAdventuresAdapter extends
        RecyclerView.Adapter<NewFriendsAdventuresAdapter.ViewHolder> {

    private static final String TAG = "NewFriendsAdventuresAdapter";

    //vars
    ArrayList<User> users;
    private Context mContext;

    public NewFriendsAdventuresAdapter(Context context, ArrayList<User> users) {

        this.users = users;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_adventure_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(users.get(position).getImage())
                .into(holder.image);

        holder.username.setText(users.get(position).getUsername());
        holder.title.setText(Administer.getInstance()
                .getUserAdventure(users.get(position).getUsername()).get(0).getTitle());


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView username;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circle_image_view);
            username = itemView.findViewById(R.id.new_adventures_username);
            title = itemView.findViewById(R.id.new_adventures_title);
        }
    }
}
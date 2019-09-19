package com.rasm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rasm.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewFriendsAdventuresAdapter extends
        RecyclerView.Adapter<NewFriendsAdventuresAdapter.ViewHolder> {

    private static final String TAG = "NewFriendsAdventuresAdapter";

    //vars
    private List<String> userNames = new ArrayList<>();
    private List<String> adventureTitles = new ArrayList<>();
    private List<Integer> profileImages = new ArrayList<>();
    private Context mContext;

    public NewFriendsAdventuresAdapter(Context context, ArrayList<String> userNames
            , ArrayList<String> adventureTitles, ArrayList<Integer> profileImages) {

        this.userNames = userNames;
        this.adventureTitles = adventureTitles;
        this.profileImages = profileImages;

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
                .load(profileImages.get(position))
                .into(holder.image);

        holder.username.setText(userNames.get(position));
        holder.title.setText(adventureTitles.get(position));


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return profileImages.size();
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
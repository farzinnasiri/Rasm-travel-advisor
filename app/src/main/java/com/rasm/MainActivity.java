package com.rasm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.rasm.Administering.Administer;
import com.rasm.Administering.User;
import com.rasm.adapters.NewFriendsAdventuresAdapter;
import com.rasm.adapters.OptionsRecyclerViewAdapter;
import com.rasm.adapters.SuggestedPlacesRecyclerViewAdapter;
import com.rasm.adventures.Adventure;
import com.rasm.adventures.Place;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ImageView avatar;
    private TextView username;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        initRecyclerViewers();


    }

    private void initUI() {
       username = findViewById(R.id.dashboard_txt_username);
        avatar = findViewById(R.id.dashboard_user_photo);
        score = findViewById(R.id.dashboard_txt_points);


        username.setText(Administer.getInstance().getUsername());


        User user = Administer.getInstance().getUser(Administer.getInstance().getUsername());

        avatar.setImageResource(user.getImage());
        score.setText(user.getScore());
    }


    private void initRecyclerViewers() {

        String username = Administer.getInstance().getUsername();

        ArrayList<String> titles = new ArrayList<>();
        titles.add("ماجراجویی");
        titles.add("تنظیمات");
        titles.add("درباره ما");
        ArrayList<Integer> optionsImages = new ArrayList<>();
        optionsImages.add(R.drawable.adventures);
        optionsImages.add(R.drawable.setting);
        optionsImages.add(R.drawable.info);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        RecyclerView optionsRecyclerView = findViewById(R.id.options_list);
        optionsRecyclerView.setLayoutManager(layoutManager);
        OptionsRecyclerViewAdapter adapter = new OptionsRecyclerViewAdapter(this, titles,
                optionsImages);
        optionsRecyclerView.setAdapter(adapter);



        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        RecyclerView newAdventuresRecyclerView = findViewById(R.id.new_adventures_list);
        newAdventuresRecyclerView.setLayoutManager(layoutManager1);



        NewFriendsAdventuresAdapter adapter2 = new NewFriendsAdventuresAdapter(
                this,Administer.getInstance().getUserFriends(username));


        newAdventuresRecyclerView.setAdapter(adapter2);

        ArrayList<Place> places = new ArrayList<>();

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.image);

        places.add(new Place("تخت جمشید","یک جای خیلی باحالا با کلی چیز میز قدیمی","آثار باستانی","شیراز",images));
        places.add(new Place("آرامگاه سعدی","شیخ اجل سعدی","آثار باستانی","شیراز",images));
        places.add(new Place("آرامگاه حافظ","لسان الغیب شیرازی","آثار باستانی","شیراز",images));
        places.add(new Place("پل طبیعت","یک پل فوق العاده","مکان دیدنی","تهران",images));
        places.add(new Place("تلکابین رامسر","تلکابین سواری در شمال کشور","طبیعت گردی","مازندارن",images));
        places.add(new Place("کیش","فوق العاده ترین جزیره ایران","استراحتی","جزیره کیش",images));

        LinearLayoutManager suggestedPlacesLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        RecyclerView suggestedPlacesRecyclerView = findViewById(R.id.sugested_places_list);
        suggestedPlacesRecyclerView.setLayoutManager(suggestedPlacesLayoutManager);
        SuggestedPlacesRecyclerViewAdapter suggestedPlacesRecyclerViewAdapter =
                new SuggestedPlacesRecyclerViewAdapter(this,places);

        suggestedPlacesRecyclerView.setAdapter(suggestedPlacesRecyclerViewAdapter);
    }


}

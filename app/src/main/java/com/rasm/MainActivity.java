package com.rasm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.rasm.Administering.Administer;
import com.rasm.adapters.NewFriendsAdventuresAdapter;
import com.rasm.adapters.OptionsRecyclerViewAdapter;
import com.rasm.adapters.SuggestedPlacesRecyclerViewAdapter;

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



        HashMap userData = Administer.getInstance().getUserDatas(username.getText().toString());



        username.setText(Administer.getInstance().getUsername());
        avatar.setImageBitmap((Bitmap) (userData.get("image")));
        score.setText((String)(userData.get("score")));
    }


    private void initRecyclerViewers() {

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


        ArrayList<String> newAdventuresTitles = new ArrayList<>();
        newAdventuresTitles.add("آرامگاه سعدی");
        newAdventuresTitles.add("تخت جمشید");
        newAdventuresTitles.add("کاخ نیاوران");
        newAdventuresTitles.add("آرامگاه حافظ");

        ArrayList<String> userNames = new ArrayList<>();
        userNames.add("کریم بزغاله");
        userNames.add("علی گوساله");
        userNames.add("لوبیا چشم بلبلی");
        userNames.add("جمشید اوسکوله");

        ArrayList<Integer> usersImages = new ArrayList<>();
        usersImages.add(R.drawable.ic_girl);
        usersImages.add(R.drawable.ic_man);
        usersImages.add(R.drawable.ic_girl2);
        usersImages.add(R.drawable.ic_boy);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        RecyclerView newAdventuresRecyclerView = findViewById(R.id.new_adventures_list);
        newAdventuresRecyclerView.setLayoutManager(layoutManager1);
        NewFriendsAdventuresAdapter adapter2 = new NewFriendsAdventuresAdapter(this, userNames,
                newAdventuresTitles,
                usersImages);
        newAdventuresRecyclerView.setAdapter(adapter2);




        LinearLayoutManager suggestedPlacesLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        RecyclerView suggestedPlacesRecyclerView = findViewById(R.id.sugested_places_list);
        suggestedPlacesRecyclerView.setLayoutManager(suggestedPlacesLayoutManager);
        SuggestedPlacesRecyclerViewAdapter suggestedPlacesRecyclerViewAdapter =
                new SuggestedPlacesRecyclerViewAdapter(this,Administer.getInstance()
                        .getSuggestedPlace(4));

        suggestedPlacesRecyclerView.setAdapter(suggestedPlacesRecyclerViewAdapter);
    }


}

package com.rasm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.rasm.adapters.NewFriendsAdventuresAdapter;
import com.rasm.adapters.OptionsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAvatar();


        initRecyclerViewers();

    }

    private void initAvatar() {
        avatar = findViewById(R.id.dashboard_user_photo);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this , UserProfileActivity.class));
            }
        });
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
        NewFriendsAdventuresAdapter adapter2 = new NewFriendsAdventuresAdapter(this,userNames,
                newAdventuresTitles,
                usersImages);
        newAdventuresRecyclerView.setAdapter(adapter2);

    }




}

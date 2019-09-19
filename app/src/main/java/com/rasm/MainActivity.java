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
import android.widget.ImageButton;
import android.widget.ImageView;

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
        optionsImages.add(R.drawable.ic_settings);
        optionsImages.add(R.drawable.ic_info);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.options_list);
        recyclerView.setLayoutManager(layoutManager);
        OptionsRecyclerViewAdapter adapter = new OptionsRecyclerViewAdapter(this, titles,
                optionsImages);
        recyclerView.setAdapter(adapter);

    }




}

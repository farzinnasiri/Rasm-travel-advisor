package com.rasm;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rasm.adapters.ImageAdapter;

import java.util.ArrayList;

public class DetailedAdventureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_adventure);

        ViewPager viewPager = findViewById(R.id.image_slider_viewer);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.image);
        images.add(R.drawable.hafez2);

        ImageAdapter adapter = new ImageAdapter(this,images);
        viewPager.setAdapter(adapter);

    }
}

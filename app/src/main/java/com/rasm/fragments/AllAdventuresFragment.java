package com.rasm.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rasm.Administering.Administer;
import com.rasm.LoginActivity;
import com.rasm.MainActivity;
import com.rasm.R;
import com.rasm.adapters.AdventuresRecyclerViewAdapter;
import com.rasm.adventures.Adventure;

import java.util.ArrayList;

public class AllAdventuresFragment extends Fragment {

    RecyclerView adventures;
    SwipeRefreshLayout swipeRefreshLayout;


    public AllAdventuresFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_adventures, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Thread myThread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1500);
                            swipeRefreshLayout.setRefreshing(false);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                myThread.start();
            }
        });


        adventures = view.findViewById(R.id.all_adventures_list);
        adventures.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Adventure> adventuresList = new ArrayList<>();
        ArrayList<Bitmap> images = new ArrayList<>();
        images.add(BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.image));
        adventuresList.add(new Adventure("رضا القاسی زاده",
                "یه روز خوب در شاه چراغ",
                "امروز","سفری بود فوق العاده به همراه دوستان خوبم",images));
        adventuresList.add(new Adventure("محمد فرزین",
                "یک هفته در شیراز",
                "دیروز","مدت ها بود که به این شهر سفر نکرده بودم",images));
        adventuresList.add(new Adventure("بردیا چهارتار",
                "وان،شهری فوق العاده دیدنی",
                "هفته پیش","استراحتی بود که بهش نیاز داشتم",images));
        adventuresList.add(new Adventure("کاوه پورمقام",
                "ماه عسل در جزایر قناری",
                "ماه پیش","بهترین هفته زندگیم",images));

        AdventuresRecyclerViewAdapter adventuresRecyclerViewAdapter =
                new AdventuresRecyclerViewAdapter(getContext(),adventuresList);

        adventures.setAdapter(adventuresRecyclerViewAdapter);




        return view;
    }

}

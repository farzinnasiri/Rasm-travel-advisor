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
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.image);
        adventuresList.add(new Adventure(1,
                "یه روز خوب در شاه چراغ",
                "امروز","سفری بود فوق العاده به همراه دوستان خوبم",images));
        adventuresList.add(new Adventure(2,
                "یک هفته در شیراز",
                "دیروز","مدت ها بود که به این شهر سفر نکرده بودم",images));
        adventuresList.add(new Adventure(3,
                "تور جهان نما",
                "امروز","تور ش برنامه های خیلی خوبی داشت با قیمت مناسب... پیشنهاد میکنم",images));
        adventuresList.add(new Adventure(4,
                "بهشت گمشده",
                "چند روز پیش","متاسفانه طبیعتش دیگه به اون بکری سابق نبود",images));
        adventuresList.add(new Adventure(5,
                "وان،شهری فوق العاده دیدنی",
                "هفته پیش","استراحتی بود که بهش نیاز داشتم",images));
        adventuresList.add(new Adventure(6,
                "یک هفته جزایر قشم",
                "این هفته","بهترین هفته زندگیم",images));
        adventuresList.add(new Adventure(7,
                "غار بورنیک",
                "پریروز","یه همچین  حالتی داشتیم ما توی این چن روز",images));
        adventuresList.add(new Adventure(8,
                "کوه سولقان",
                "امروز","خیلی خوش گذشت جای اقا پرویز خالی",images));
        adventuresList.add(new Adventure(9,
                "چرا عاقل چرا عاقل",
                "امروز","چرا عاقل کند کاری که باز آرد پشیمانی",images));



        AdventuresRecyclerViewAdapter adventuresRecyclerViewAdapter =
                new AdventuresRecyclerViewAdapter(getContext(),adventuresList);

        adventures.setAdapter(adventuresRecyclerViewAdapter);




        return view;
    }

}

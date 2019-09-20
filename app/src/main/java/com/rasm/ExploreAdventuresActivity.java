package com.rasm;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import io.github.meness.Library.Utils.Utility;

import com.rasm.fragments.AllAdventuresFragment;
import com.rasm.fragments.MyAdventuresFragment;

public class ExploreAdventuresActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private TextView txtToolbarTitle;

    private AllAdventuresFragment allAdventuresFragment;
    private MyAdventuresFragment myAdventuresFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_adventures);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        toolbar=findViewById(R.id.toolbar_main);
        txtToolbarTitle=findViewById(R.id.txtToolbarTitle);

        txtToolbarTitle.setTypeface( Typeface.createFromAsset(getAssets(),"vazir.ttf"));

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        allAdventuresFragment = new AllAdventuresFragment();
        myAdventuresFragment = new MyAdventuresFragment();

        Utility.ViewPagerAdapter adapter = new Utility
                .ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(allAdventuresFragment);
        adapter.addFragment(myAdventuresFragment);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        int[] icons = {R.drawable.ic_globe,R.drawable.ic_person};

        tabLayout.getTabAt(0).setIcon(icons[0]);
        tabLayout.getTabAt(1).setIcon(icons[1]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setToolbarTitle(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setToolbarTitle(int index) {
        if (index == 0)
            txtToolbarTitle.setText("ماجراجویی های دوستان");
        else if (index == 1)
            txtToolbarTitle.setText("ماجراجویی های من");

    }



}

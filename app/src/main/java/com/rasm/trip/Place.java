package com.rasm.trip;

import android.media.Image;

import java.util.ArrayList;

public class Place {
    private String gps;
    private ArrayList<Image> pictures;
    private String title;
    private String description;

    public Place(String gps, ArrayList<Image> pictures, String title, String description) {
        this.gps = gps;
        this.pictures = pictures;
        this.title = title;
        this.description = description;
    }
}

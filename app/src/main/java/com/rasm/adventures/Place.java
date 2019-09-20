package com.rasm.adventures;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;


public class Place {

    private String descriptions, id, title;
    private ArrayList<Integer> images;

    public Place(HashMap map) {

//        initializeFields(map);
//        orderDescriptionsWithImages();

    }

    public Place(String title, String descriptions, ArrayList<Integer> images) {

        this.images = images;
        this.descriptions = descriptions;
        this.title = title;
    }

    private void initializeFields(HashMap map) {

    }

    public ArrayList<Integer> getPictures() {
        return images;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }
}

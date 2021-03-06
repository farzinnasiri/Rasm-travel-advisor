package com.rasm.adventures;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;


public class Place {

    private String descriptions, id, name;
    private String type;
    private String location;
    private ArrayList<Integer> images;


    public Place(String name, String descriptions,String type, String location, ArrayList<Integer> images) {

        this.images = images;
        this.descriptions = descriptions;
        this.name = name;
        this.type = type;
        this.location = location;
    }

    private void initializeFields(HashMap map) {

    }

    public ArrayList<Integer> getPictures() {
        return images;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getImages() {
        return images;
    }

    public void setImages(ArrayList<Integer> images) {
        this.images = images;
    }
}

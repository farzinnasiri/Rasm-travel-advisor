package com.rasm.Administering;

import com.rasm.adventures.Adventure;

import java.util.ArrayList;

public class User {

    private String username , score , email , phone , pass;
            private int visibility;
    private ArrayList<User> friends;
    private ArrayList<Integer> AdventuresId;
    private int image;

    public User(String username, String score, String email, String phone, String pass, int visibility, ArrayList<User> friends, int image , ArrayList<Integer> AdventuresId) {
        this.username = username;
        this.score = score;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.visibility = visibility;
        this.friends = friends;
        this.image = image;
        this.AdventuresId = AdventuresId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public  void addFriend(User friend){
        friends.add(friend);
    }

    public void addAdventure(Adventure adventure){
        AdventuresId.add(adventure.getId());
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

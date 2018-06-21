package com.alpha.whizwish.Models;

import java.util.ArrayList;

public class FolowersModel {
    private int image;
    private String userName;
    private String city;
    private boolean following;
    public FolowersModel() {
    }

    public FolowersModel(int image, String userName, String city, boolean following) {
        this.image = image;
        this.userName = userName;
        this.city = city;
        this.following = following;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}



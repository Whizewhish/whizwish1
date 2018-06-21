package com.alpha.whizwish.Models;

public class GiftsModel {

    private String userName;
    private int userImage;
    private int giftImage;
    private boolean recieved;
    private int recievedDays;

    public GiftsModel(String userName, int userImage, int giftImage, boolean recieved, int recievedDays) {
        this.userName = userName;
        this.userImage = userImage;
        this.giftImage = giftImage;
        this.recieved = recieved;
        this.recievedDays = recievedDays;
    }

    public GiftsModel() {

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }


    public int getGiftImage() {
        return giftImage;
    }

    public void setGiftImage(int giftImage) {
        this.giftImage = giftImage;
    }

    public boolean isRecieved() {
        return recieved;
    }

    public void setRecieved(boolean recieved) {
        this.recieved = recieved;
    }

    public int getRecievedDays() {
        return recievedDays;
    }

    public void setRecievedDays(int recievedDays) {
        this.recievedDays = recievedDays;
    }
}
